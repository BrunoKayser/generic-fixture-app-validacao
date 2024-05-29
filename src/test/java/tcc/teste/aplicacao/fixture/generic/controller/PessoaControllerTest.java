package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tcc.teste.aplicacao.fixture.generic.PessoaRequestFixture;
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
public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private InserirPessoaService inserirPessoaService;

    @Mock
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaMapper pessoaMapper;

    @Test
    public void testInsert() {
        // Arrange
        PessoaRequest pessoaRequest = PessoaRequestFixture.getPessoaRequest();
        PessoaInseridaResponse pessoaInseridaResponse = new PessoaInseridaResponse();
        when(pessoaMapper.toPessoa(any())).thenReturn(new Pessoa());
        when(inserirPessoaService.inserir(any())).thenReturn(new PessoaInserida());
        when(pessoaMapper.toPessoaInseridaResponse(any())).thenReturn(pessoaInseridaResponse);

        // Act
        ResponseEntity<PessoaInseridaResponse> response = pessoaController.insert(pessoaRequest);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void testGetPessoa() {
        // Arrange
        Long id = 1L;
        PessoaResponse pessoaResponse = new PessoaResponse();
        when(consultarPessoaService.consultar(any())).thenReturn(new Pessoa());
        when(pessoaMapper.toPessoaResponse(any())).thenReturn(pessoaResponse);

        // Act
        ResponseEntity<PessoaResponse> response = pessoaController.getPessoa(id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}