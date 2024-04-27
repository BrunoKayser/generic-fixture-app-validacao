package tcc.teste.aplicacao.fixture.generic.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {

    @InjectMocks
    PessoaMapper pessoaMapper;

    @Mock
    ModelMapper modelMapper;

    @Test
    void deveMapearPessoaRequestParaPessoaComSucesso(){
        Pessoa expected = PessoaFixture.pessoa();
        PessoaRequest pessoaRequest = PessoaFixture.pessoaRequest();

        Mockito.when(modelMapper.map(any(),any())).thenReturn(expected);

        Pessoa actual = pessoaMapper.toPessoa(pessoaRequest);

        assertEquals(expected,actual);

    }

    @Test
    void deveMapearPessoaParaPessoaResponseComSucesso(){
        Pessoa pessoa = PessoaFixture.pessoa();
        PessoaResponse expected = PessoaFixture.pessoaResponse();

        Mockito.when(modelMapper.map(any(),any())).thenReturn(expected);

        PessoaResponse actual = pessoaMapper.toPessoaResponse(pessoa);

        assertEquals(expected,actual);
    }

    @Test
    void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso(){
        PessoaInserida pessoaInserida = PessoaInserida.builder().id(1l).build();
        PessoaInseridaResponse expected = PessoaInseridaResponse.builder().id(1l).build();

        Mockito.when(modelMapper.map(any(),any())).thenReturn(expected);

        PessoaInseridaResponse actual = pessoaMapper.toPessoaInseridaResponse(pessoaInserida);

        assertEquals(expected,actual);
    }

}