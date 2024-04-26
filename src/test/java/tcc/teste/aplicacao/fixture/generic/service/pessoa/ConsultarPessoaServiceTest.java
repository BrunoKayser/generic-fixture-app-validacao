package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @InjectMocks
    private ConsultarPessoaService tested;

    @Mock
    private PessoaRepository repository;

    @Test
    public void deveConsultarComSucesso() {
        Pessoa pessoa = PessoaFixture.criarPessoa();
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(pessoa));

        var result = tested.consultar(id);

        assertEquals(result, pessoa);
    }

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        Long id = 1L;
        NaoEncontradoException exception = new NaoEncontradoException(String.format("Pessoa com %d não encontrada", id));
        when(repository.findById(id)).thenThrow(exception);

        var result = assertThrows(NaoEncontradoException.class,
                                        () -> tested.consultar(id));

        assertEquals(result, exception);
    }

}