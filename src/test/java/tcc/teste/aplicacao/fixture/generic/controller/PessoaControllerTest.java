package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @InjectMocks
    PessoaController pessoaController;

    @Mock
    InserirPessoaService inserirPessoaService;

    @Mock
    ConsultarPessoaService consultarPessoaService;
    @Mock
    PessoaMapper pessoaMapper;

    @Test
    void deveInserirComSucesso() {

        PessoaInseridaResponse expected = PessoaInseridaResponse.builder().id(1l).build();
        PessoaInserida pessoaInserida = PessoaInserida.builder().id(1l).build();
        PessoaRequest pessoaRequestFixture = PessoaFixture.pessoaRequest();
        Pessoa pessoaFixture = PessoaFixture.pessoa();

        Mockito.when(pessoaMapper.toPessoa(pessoaRequestFixture)).thenReturn(pessoaFixture);
        Mockito.when(inserirPessoaService.inserir(pessoaFixture)).thenReturn(pessoaInserida);
        Mockito.when(pessoaMapper.toPessoaInseridaResponse(pessoaInserida)).thenReturn(expected);

        ResponseEntity<PessoaInseridaResponse> actual = pessoaController.insert(pessoaRequestFixture);

        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
        assertEquals(expected, actual.getBody());

    }

    @Test
    void deveConsultarComSucesso() {
        Long idPessoa = 1l;
        Pessoa pessoa = PessoaFixture.pessoa();
        PessoaResponse expected = PessoaFixture.pessoaResponse();

        Mockito.when(consultarPessoaService.consultar(idPessoa)).thenReturn(pessoa);
        Mockito.when(pessoaMapper.toPessoaResponse(pessoa)).thenReturn(expected);

        ResponseEntity<PessoaResponse> actual = pessoaController.getPessoa(idPessoa);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

}