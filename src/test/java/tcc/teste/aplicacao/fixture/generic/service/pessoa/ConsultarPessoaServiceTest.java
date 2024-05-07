package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @InjectMocks
    ConsultarPessoaService service;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void deveConsultarComSucesso() {
        when(pessoaRepository.findById(any())).thenReturn(Optional.of(getPessoa()));

        var result = assertDoesNotThrow(() -> service.consultar(1L));

        assertNotNull(result.getId());
        assertNotNull(result.getAnimaisDeEstimacao());
        assertNotNull(result.getNome());
        assertNotNull(result.getApelido());
        assertNotNull(result.getGenero());
        assertNotNull(result.getSobrenome());
        assertNotNull(result.getDataNascimento());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        when(pessoaRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.consultar(1L));
    }

    private Pessoa getPessoa() {
        return Pessoa.builder()
                .id(1L)
                .animaisDeEstimacao(emptyList())
                .nome("Lucas")
                .apelido("Loky")
                .genero(GeneroEnum.NAO_BINARIO)
                .sobrenome("Rech")
                .dataNascimento(LocalDate.of(1995, 11, 30))
                .build();
    }

}