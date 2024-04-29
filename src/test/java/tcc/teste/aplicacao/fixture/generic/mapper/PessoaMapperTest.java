package tcc.teste.aplicacao.fixture.generic.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.controller.Fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.controller.Fixture.PessoaRequestFixture;
import tcc.teste.aplicacao.fixture.generic.controller.Fixture.PessoaResponseFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PessoaMapper pessoaMapper;

    @Test
    void toPessoa() {
        var pessoaRequest = PessoaRequestFixture.criaPessoa();
        var pessoa = PessoaFixture.criaPessoa();
        when(modelMapper.map(pessoaRequest, Pessoa.class)).thenReturn(pessoa);

        var response = pessoaMapper.toPessoa(pessoaRequest);

        assertEquals(pessoa, response);
    }

    @Test
    void toPessoaResponse() {
        var pessoa = PessoaFixture.criaPessoa();
        var pessoaResponse = PessoaResponseFixture.criaPessoa();

        when(modelMapper.map(pessoa, PessoaResponse.class)).thenReturn(pessoaResponse);

        var response = pessoaMapper.toPessoaResponse(pessoa);

        assertEquals(pessoaResponse, response);
    }

    @Test
    void toPessoaInseridaResponse() {
        when(modelMapper.map(any(), any())).thenReturn(new PessoaInseridaResponse(1L));

        var response = pessoaMapper.toPessoaInseridaResponse(new PessoaInserida(1L));

        assertEquals(1L, response.getId());
    }
}