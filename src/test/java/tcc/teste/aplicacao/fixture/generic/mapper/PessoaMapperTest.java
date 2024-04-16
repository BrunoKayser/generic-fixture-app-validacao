package tcc.teste.aplicacao.fixture.generic.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.controller.utils.MockUtils.*;

@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {

    @InjectMocks
    private PessoaMapper pessoaMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void deveMapearPessoaRequestParaPessoaComSucesso() {
        // given
        PessoaRequest pessoaRequest = mockPessoaRequest();
        Pessoa pessoa = mockPessoa();
        when(modelMapper.map(pessoaRequest, Pessoa.class)).thenReturn(pessoa);

        // when
        Pessoa result = pessoaMapper.toPessoa(pessoaRequest);

        // then
        assertEquals(result, pessoa);
    }

    @Test
    void deveMapearPessoaParaPessoaResponseComSucesso() {
        // given
        Pessoa pessoa = mockPessoa();
        PessoaResponse pessoaResponse = mockPessoaResponse();
        when(modelMapper.map(pessoa, PessoaResponse.class)).thenReturn(pessoaResponse);

        // when
        PessoaResponse result = pessoaMapper.toPessoaResponse(pessoa);

        // then
        assertEquals(result, pessoaResponse);
    }

    @Test
    void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {
        // given
        PessoaInserida pessoaInserida = mockPessoaInserida(1L);
        PessoaInseridaResponse pessoaInseridaResponse = mockPessoaInseridaResponse(1L);
        when(modelMapper.map(pessoaInserida, PessoaInseridaResponse.class)).thenReturn(pessoaInseridaResponse);

        // when
        PessoaInseridaResponse result = pessoaMapper.toPessoaInseridaResponse(pessoaInserida);

        // then
        assertEquals(result, pessoaInseridaResponse);
    }
}