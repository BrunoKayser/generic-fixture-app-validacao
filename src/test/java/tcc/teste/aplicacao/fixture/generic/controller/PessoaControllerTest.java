package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.util.Mocks.*;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private InserirPessoaService inserirPessoaService;
    @Mock
    private ConsultarPessoaService consultarPessoaService;
    @Mock
    private PessoaMapper pessoaMapper;
    
    @Test
    @DisplayName("Deve inserir com sucesso")
    void deveInserirComSucesso() {
        PessoaRequest pessoaRequest = gerarPessoaRequest();
        Pessoa pessoa = gerarPessoa();
        PessoaInserida pessoaInserida = gerarPessoaInserida();
        PessoaInseridaResponse pessoaInseridaResponse = gerarPessoaInseridaResponse();

        when(pessoaMapper.toPessoa(any())).thenReturn(pessoa);
        when(inserirPessoaService.inserir(any())).thenReturn(pessoaInserida);
        when(pessoaMapper.toPessoaInseridaResponse(any())).thenReturn(pessoaInseridaResponse);

        PessoaInseridaResponse result = pessoaController.insert(pessoaRequest).getBody();

        assertEquals(pessoaInseridaResponse, result);
    }

    @Test
    @DisplayName("Deve consultar com sucesso")
    void deveConsultarComSucesso() {
        Long id = 20L;
        PessoaResponse pessoaResponse = gerarPessoaResponse();

        when(consultarPessoaService.consultar(any())).thenReturn(gerarPessoa());
        when(pessoaMapper.toPessoaResponse(any())).thenReturn(pessoaResponse);

        PessoaResponse result = pessoaController.getPessoa(id).getBody();

        assertEquals(pessoaResponse, result);
    }
}