package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ConsultarPessoaServiceTest {

    @InjectMocks
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void deveConsultarComSucesso() {
        // Arrange
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.findById(any(Long.class))).thenReturn(Optional.of(pessoa));

        // Act
        Pessoa result = consultarPessoaService.consultar(id);

        // Assert
        assertEquals(pessoa, result);
    }

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        // Arrange
        Long id = 1L;
        when(pessoaRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NaoEncontradoException.class, () -> consultarPessoaService.consultar(id));
    }
}