package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.controller.Fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    ConsultarPessoaService consultarPessoaService;

    @Test
    void deveConsultarComSucesso() {
        var pessoa = PessoaFixture.criaPessoa();
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        var response = consultarPessoaService.consultar(1L);
        assertEquals(pessoa, response);
    }
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> consultarPessoaService.consultar(1L));
    }
}