package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomPessoa;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {
    @InjectMocks
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void deveConsultarComSucesso() {
        var expected = randomPessoa();

        when(pessoaRepository.findById(any()))
            .thenReturn(Optional.of(expected));

        var result = consultarPessoaService.consultar(nextLong());

        assertEquals(expected, result);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        when(pessoaRepository.findById(any()))
            .thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> consultarPessoaService.consultar(nextLong()));
    }
}