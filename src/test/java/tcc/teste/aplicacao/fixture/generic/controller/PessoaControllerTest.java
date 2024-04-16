package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.controller.utils.MockUtils.*;


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
    void deveInserirComSucesso() {
        PessoaRequest pessoaRequest = mockPessoaRequest();
        when(inserirPessoaService.inserir(any())).thenReturn(mockPessoaInserida(1L));
        when(pessoaMapper.toPessoa(any())).thenReturn(mockPessoa());
        when(pessoaMapper.toPessoaInseridaResponse(any())).thenReturn(mockPessoaInseridaResponse(1L));

        ResponseEntity<PessoaInseridaResponse> response = pessoaController.insert(pessoaRequest);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody().getId(), 1L);
    }

    @Test
    void deveConsultarComSucesso() {
        Long id = 1l;
        Pessoa pessoa = mockPessoa();
        when(consultarPessoaService.consultar(id)).thenReturn(pessoa);
        when(pessoaMapper.toPessoaResponse(any())).thenReturn(mockPessoaResponse());

        ResponseEntity<PessoaResponse> response = pessoaController.getPessoa(id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNome(), pessoa.getNome());
        assertEquals(response.getBody().getAnimaisDeEstimacao().size(), pessoa.getAnimaisDeEstimacao().size());
        assertEquals(response.getBody().getAnimaisDeEstimacao().get(0).getNome(), pessoa.getAnimaisDeEstimacao().get(0).getNome());
        assertEquals(response.getBody().getGenero(), pessoa.getGenero());
        assertEquals(response.getBody().getDataNascimento(), pessoa.getDataNascimento());
        assertEquals(response.getBody().getApelido(), pessoa.getApelido());
        assertEquals(response.getBody().getSobrenome(), pessoa.getSobrenome());
    }
}