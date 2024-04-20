package tcc.teste.aplicacao.fixture.generic.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomPessoa;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomPessoaInserida;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomPessoaInseridaResponse;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomPessoaRequest;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomPessoaResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {
    @InjectMocks
    private PessoaMapper pessoaMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void deveMapearPessoaRequestParaPessoaComSucesso() {
        var expected = randomPessoa();

        when(modelMapper.map(any(), eq(Pessoa.class)))
            .thenReturn(expected);

        var result = pessoaMapper.toPessoa(randomPessoaRequest());

        assertEquals(expected, result);
    }

    @Test
    void deveMapearPessoaParaPessoaResponseComSucesso() {
        var expected = randomPessoaResponse();

        when(modelMapper.map(any(), eq(PessoaResponse.class)))
            .thenReturn(expected);

        var result = pessoaMapper.toPessoaResponse(randomPessoa());

        assertEquals(expected, result);
    }

    @Test
    void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {
        var expected = randomPessoaInseridaResponse();

        when(modelMapper.map(any(), eq(PessoaInseridaResponse.class)))
            .thenReturn(expected);

        var result = pessoaMapper.toPessoaInseridaResponse(randomPessoaInserida());

        assertEquals(expected, result);
    }
}