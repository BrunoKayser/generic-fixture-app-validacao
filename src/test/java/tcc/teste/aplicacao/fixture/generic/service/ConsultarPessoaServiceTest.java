package tcc.teste.aplicacao.fixture.generic.service;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @InjectMocks
    ConsultarPessoaService consultarPessoaService;

    @Mock
    PessoaRepository pessoaRepository;

    @Test
    void deveConsultarComSucesso() {
        var id = 1L;

        var pessoa = GenericFixture.generate(Pessoa.class);

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));

        var pessoaRetorno = consultarPessoaService.consultar(id);

        assertEquals(pessoa, pessoaRetorno);

    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        var id = 1L;

        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(NaoEncontradoException.class,
                () -> consultarPessoaService.consultar(id));

        assertEquals(String.format("Pessoa com %d não encontrada", id), exception.getMessage());

    }


}