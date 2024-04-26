package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.*;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @InjectMocks
    private PessoaController tested;

    @Mock
    private InserirPessoaService inserirPessoaService;

    @Mock
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaMapper pessoaMapper;

    @Test
    public void deveInserirComSucesso() {
        PessoaRequest request = PessoaRequestFixture.criarPessoaRequest();
        Pessoa pessoa = PessoaFixture.criarPessoa();
        PessoaInserida pessoaInserida = PessoaInseridaFixture.criarPessoaInserida();
        PessoaInseridaResponse response = PessoaInseridaResponseFixture.criarPessoaInseridaResponse();

        when(pessoaMapper.toPessoa(request)).thenReturn(pessoa);
        when(inserirPessoaService.inserir(pessoa)).thenReturn(pessoaInserida);
        when(pessoaMapper.toPessoaInseridaResponse(pessoaInserida)).thenReturn(response);

        var result = tested.insert(request);

        assertAll("Inserir Pessoa Assertions",
                () -> assertEquals(result.getStatusCode(), HttpStatus.CREATED),
                () -> assertEquals(result.getBody(), response)
        );
    }

    @Test
    public void deveConsultarComSucesso() {
        Long pathVariable = 1L;
        Pessoa pessoa = PessoaFixture.criarPessoa();
        PessoaResponse pessoaResponse = PessoaResponseFixture.criarPessoaResponse();
        when(consultarPessoaService.consultar(pathVariable)).thenReturn(pessoa);
        when((pessoaMapper.toPessoaResponse(pessoa))).thenReturn(pessoaResponse);

        var result = tested.getPessoa(pathVariable);

        assertEquals(result.getBody(), pessoaResponse);
    }

}