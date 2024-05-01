package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @InjectMocks
    private ConsultarPessoaService service;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void consultar() {
        Long id = 123L;

        Optional<Pessoa> pessoa = Optional.of(getPessoaFixture());

        Mockito.doReturn(pessoa).when(pessoaRepository).findById(id);

        Pessoa result = service.consultar(id);

        Assertions.assertEquals(pessoa.get(), result);
    }

    @Test
    void consultarComErro() {
        Long id = 123L;

        Mockito.doReturn(Optional.empty()).when(pessoaRepository).findById(id);

        Assertions.assertThrows(NaoEncontradoException.class, () -> service.consultar(id));
    }

    private Pessoa getPessoaFixture() {
        return Pessoa.builder()
                .nome("teste")
                .apelido("abc")
                .dataNascimento(LocalDate.now())
                .build();
    }
}