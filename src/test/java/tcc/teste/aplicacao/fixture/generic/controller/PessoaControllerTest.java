package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tcc.teste.aplicacao.fixture.generic.controller.fixture.*;
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

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @InjectMocks
    PessoaController pessoaController;

    @Mock
    private InserirPessoaService inserirPessoaService;

    @Mock
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaMapper pessoaMapper;

    @Test
    public void deveInserirComSucesso() {
        PessoaRequest pessoaRequest = PessoaRequestFixture.create();
        PessoaInserida pessoaInserida = PessoaInseridaFixture.create();
        Pessoa pessoa = PessoaFixture.create();
        PessoaInseridaResponse pessoaInseridaResponse = PessoaInseridaResponseFixture.create();

        when(inserirPessoaService.inserir(any(Pessoa.class))).thenReturn(pessoaInserida);
        when(pessoaMapper.toPessoa(any(PessoaRequest.class))).thenReturn(pessoa);
        when(pessoaMapper.toPessoaInseridaResponse(any(PessoaInserida.class))).thenReturn(pessoaInseridaResponse);

        ResponseEntity<PessoaInseridaResponse> responseEntity = pessoaController.insert(pessoaRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(pessoaInseridaResponse, responseEntity.getBody());
    }

    @Test
    public void deveConsultarComSucesso() {
        PessoaResponse pessoaResponse = PessoaResponseFixture.create();
        Pessoa pessoa = PessoaFixture.create();

        when(consultarPessoaService.consultar(any(Long.class))).thenReturn(pessoa);
        when(pessoaMapper.toPessoaResponse(any(Pessoa.class))).thenReturn(pessoaResponse);

        ResponseEntity<PessoaResponse> responseEntity = pessoaController.getPessoa(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pessoaResponse, responseEntity.getBody());
    }
}