package tcc.teste.aplicacao.fixture.generic.mapper;

import org.junit.jupiter.api.DisplayName;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.util.Mocks.*;

@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {

    @InjectMocks
    private PessoaMapper pessoaMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    @DisplayName("Deve mapear pessoa request para pessoa com sucesso")
    void deveMapearPessoaRequestParaPessoaComSucesso() {
        PessoaRequest pessoaRequest = gerarPessoaRequest();
        Pessoa pessoa = gerarPessoa();
        
        when(modelMapper.map(pessoaRequest, Pessoa.class)).thenReturn(pessoa);

        Pessoa result = pessoaMapper.toPessoa(pessoaRequest);

        assertEquals(pessoa, result);
    }

    @Test
    @DisplayName("Deve mapear pessoa para pessoa response com sucesso")
    void deveMapearPessoaParaPessoaResponseComSucesso() {
        PessoaResponse pessoaResponse = gerarPessoaResponse();
        Pessoa pessoa = gerarPessoa();

        when(modelMapper.map(pessoa, PessoaResponse.class)).thenReturn(pessoaResponse);

        PessoaResponse result = pessoaMapper.toPessoaResponse(pessoa);

        assertEquals(pessoaResponse, result);
    }

    @Test
    @DisplayName("Deve mapear pessoa inserida para pessoa inserida response com sucesso")
    void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {
        PessoaInseridaResponse pessoaInseridaResponse = gerarPessoaInseridaResponse();
        PessoaInserida pessoaInserida = gerarPessoaInserida();

        when(modelMapper.map(pessoaInserida, PessoaInseridaResponse.class)).thenReturn(pessoaInseridaResponse);

        PessoaInseridaResponse result = pessoaMapper.toPessoaInseridaResponse(pessoaInserida);

        assertEquals(pessoaInseridaResponse, result);
    }
}