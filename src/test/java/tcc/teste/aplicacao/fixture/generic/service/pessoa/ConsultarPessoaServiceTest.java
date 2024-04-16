package tcc.teste.aplicacao.fixture.generic.service.pessoa;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.controller.utils.MockUtils.mockPessoa;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @InjectMocks
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void deveConsultarComSucesso() {
        Long id = 1L;
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(mockPessoa()));

        Pessoa pessoa = consultarPessoaService.consultar(id);

        verify(pessoaRepository).findById(id);

        assertNotNull(pessoa);
        assertEquals(id, pessoa.getId());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        Long id = 1L;
        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> consultarPessoaService.consultar(id));

        verify(pessoaRepository).findById(id);
    }

}