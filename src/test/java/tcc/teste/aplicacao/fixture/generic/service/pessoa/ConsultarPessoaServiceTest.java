package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.util.Mocks.gerarPessoa;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @InjectMocks
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    @DisplayName("Deve consultar com sucesso")
    void deveConsultarComSucesso() {
        Long id = 1L;
        Pessoa pessoa = gerarPessoa();
        Optional<Pessoa> pessoaOptional = Optional.of(pessoa);

        when(pessoaRepository.findById(id)).thenReturn(pessoaOptional);

        Pessoa result = consultarPessoaService.consultar(id);

        assertEquals(pessoa, result);
    }

    @Test
    @DisplayName("Deve lancar excecao quando nao encontrar pessoa pelo id")
    void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        Long id = 1L;
        Optional<Pessoa> pessoaOptional = Optional.empty();

        when(pessoaRepository.findById(id)).thenReturn(pessoaOptional);

        NaoEncontradoException e = assertThrows(
                NaoEncontradoException.class,
                () -> consultarPessoaService.consultar(id)
        );

        assertEquals("Pessoa com 1 não encontrada", e.getMessage());
    }
}