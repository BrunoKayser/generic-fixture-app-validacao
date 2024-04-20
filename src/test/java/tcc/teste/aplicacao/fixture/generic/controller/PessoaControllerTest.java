package tcc.teste.aplicacao.fixture.generic.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.*;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {
    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private InserirPessoaService inserirPessoaService;

    @Mock
    private PessoaMapper pessoaMapper;

    @Mock
    private ConsultarPessoaService consultarPessoaService;

    @Test
    void deveInserirComSucesso() {
        var expected = randomPessoaInseridaResponse();

        when(inserirPessoaService.inserir(any()))
            .thenReturn(randomPessoaInserida());
        when(pessoaMapper.toPessoa(any()))
            .thenReturn(randomPessoa());
        when(pessoaMapper.toPessoaInseridaResponse(any()))
            .thenReturn(expected);

        var result = pessoaController.insert(randomPessoaRequest());

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(expected, result.getBody());
    }

    @Test
    void deveConsultarComSucesso() {
        var expected = randomPessoaResponse();

        when(consultarPessoaService.consultar(any()))
            .thenReturn(randomPessoa());
        when(pessoaMapper.toPessoaResponse(any()))
            .thenReturn(expected);

        var result = pessoaController.getPessoa(nextLong());

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());
    }
}