package tcc.teste.aplicacao.fixture.generic.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaInseridaFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaInseridaResponseFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaRequestFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaResponseFixture;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @InjectMocks
    PessoaController controller;

    @Mock
    InserirPessoaService inserirPessoaService;

    @Mock
    ConsultarPessoaService consultarPessoaService;

    @Mock
    PessoaMapper mapper;

    @Test
    public void insertTest() {
        var request = PessoaRequestFixture.create();
        var insertResponse = PessoaInseridaResponseFixture.create();
        when(inserirPessoaService.inserir(any())).thenReturn(PessoaInseridaFixture.create());
        when(mapper.toPessoa(any())).thenReturn(PessoaFixture.create());
        when(mapper.toPessoaInseridaResponse(any())).thenReturn(insertResponse);

        var response = controller.insert(request);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody().getId(), insertResponse.getId());
    }

    @Test
    public void getPessoaTest() {
        when(consultarPessoaService.consultar(anyLong())).thenReturn(PessoaFixture.create());
        when(mapper.toPessoaResponse(any())).thenReturn(PessoaResponseFixture.create());

        var response = controller.getPessoa(RandomUtils.nextLong());

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}