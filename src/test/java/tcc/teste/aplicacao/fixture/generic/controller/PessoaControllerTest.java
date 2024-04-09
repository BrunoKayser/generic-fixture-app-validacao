package tcc.teste.aplicacao.fixture.generic.controller;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import tcc.teste.aplicacao.fixture.generic.domain.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.InserirPessoaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        var pessoaRequest = GenericFixture.generate(PessoaRequest.class);
        var pessoa = GenericFixture.generate(Pessoa.class);
        var pessoaInserida = GenericFixture.generate(PessoaInserida.class);
        var pessoaInseridaResponse = GenericFixture.generate(PessoaInseridaResponse.class);

        when(pessoaMapper.toPessoa(pessoaRequest)).thenReturn(pessoa);
        when(inserirPessoaService.inserir(pessoa)).thenReturn(pessoaInserida);
        when(pessoaMapper.toPessoaInseridaResponse(pessoaInserida)).thenReturn(pessoaInseridaResponse);

        var retorno = pessoaController.insert(pessoaRequest);

        assertEquals(pessoaInseridaResponse, retorno.getBody());
        assertEquals(HttpStatus.CREATED, retorno.getStatusCode());

    }

    @Test
    void deveConsultarComSucesso() {

        var id = 1L;
        var pessoa = GenericFixture.generate(Pessoa.class);
        var pessoaResponse = GenericFixture.generate(PessoaResponse.class);

        when(consultarPessoaService.consultar(id)).thenReturn(pessoa);
        when(pessoaMapper.toPessoaResponse(pessoa)).thenReturn(pessoaResponse);

        var retorno = pessoaController.getPessoa(id);

        assertEquals(pessoaResponse, retorno.getBody());
        assertEquals(HttpStatus.OK, retorno.getStatusCode());

    }

}