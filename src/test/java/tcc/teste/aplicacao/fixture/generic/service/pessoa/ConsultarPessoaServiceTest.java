package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @InjectMocks
    ConsultarPessoaService consultarPessoaService;

    @Mock
    PessoaRepository pessoaRepository;

    @Test
    void deveConsultarComSucesso(){
        Pessoa pessoa = PessoaFixture.pessoa();
        Long id = 1l;

        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoa));
        Pessoa actual = consultarPessoaService.consultar(id);

        assertEquals(pessoa, actual);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId(){
        Long id = 1l;

        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> consultarPessoaService.consultar(id));
    }

}