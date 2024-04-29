package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import tcc.teste.aplicacao.fixture.generic.controller.Fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.controller.Fixture.PessoaRequestFixture;
import tcc.teste.aplicacao.fixture.generic.controller.Fixture.PessoaResponseFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @Mock
    InserirPessoaService inserirPessoaService;

    @Mock
    ConsultarPessoaService consultarPessoaService;

    @Mock
    PessoaMapper pessoaMapper;

    @InjectMocks
    PessoaController pessoaController;


    @Test
    void insert() {
        var pessoaRequest = PessoaRequestFixture.criaPessoa();
        var pessoa = PessoaFixture.criaPessoa();
        when(pessoaMapper.toPessoa(any())).thenReturn(pessoa);
        when(inserirPessoaService.inserir(any())).thenReturn(new PessoaInserida(1L));
        when(pessoaMapper.toPessoaInseridaResponse(any())).thenReturn(new PessoaInseridaResponse(1L));

        var response = pessoaController.insert(pessoaRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getPessoa() {
        var pessoa = PessoaFixture.criaPessoa();
        var pessoaResponse = PessoaResponseFixture.criaPessoa();
        when(consultarPessoaService.consultar(any())).thenReturn(pessoa);
        when(pessoaMapper.toPessoaResponse(pessoa)).thenReturn(pessoaResponse);

        var response = pessoaController.getPessoa(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}