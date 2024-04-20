package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {
    @InjectMocks
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void deveConsultarComSucesso() {
        var pessoa = new Pessoa(
                1L,
                "Nome",
                "Apelido",
                GeneroEnum.MASCULINO,
                "Sobrenome",
                LocalDate.now(),
                null
        );

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        var pessoaResult = consultarPessoaService.consultar(1L);

        assertEquals(1L, pessoaResult.getId());
        assertEquals("Nome", pessoaResult.getNome());
        assertEquals("Apelido", pessoaResult.getApelido());
    }

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> consultarPessoaService.consultar(1L));
    }
}