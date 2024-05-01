package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import java.time.LocalDate;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @InjectMocks
    private PessoaController controller;

    @Mock
    private InserirPessoaService inserirPessoaService;

    @Mock
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaMapper pessoaMapper;

    @Test
    void insert() {

        PessoaRequest request = getPessoaRequestFixture();

        Pessoa pessoa = getPessoaFixture();

        PessoaInserida pessoaInserida = getPessoaInseridaFixture();

        PessoaInseridaResponse response = getMockPessoaInseridaResponse();

        doReturn(pessoa).when(pessoaMapper).toPessoa(request);

        doReturn(pessoaInserida).when(inserirPessoaService).inserir(pessoa);

        doReturn(response).when(pessoaMapper).toPessoaInseridaResponse(pessoaInserida);

        ResponseEntity<PessoaInseridaResponse> result = controller.insert(request);


        Assertions.assertEquals(response, result.getBody());
    }

    @Test
    void getPessoa() {
        Long id = 123L;

        Pessoa pessoa = getPessoaFixture();

        PessoaResponse pessoaResponse = getMockPessoaResponse();

        doReturn(pessoa).when(consultarPessoaService).consultar(id);

        doReturn(pessoaResponse).when(pessoaMapper).toPessoaResponse(pessoa);

        ResponseEntity<PessoaResponse> result = controller.getPessoa(id);

        Assertions.assertEquals(pessoaResponse, result.getBody());
    }

    private PessoaRequest getPessoaRequestFixture() {
        return PessoaRequest.builder()
                .nome("teste")
                .apelido("abc")
                .dataNascimento(LocalDate.now())
                .build();
    }

    private Pessoa getPessoaFixture() {
        return Pessoa.builder()
                .nome("teste")
                .apelido("abc")
                .dataNascimento(LocalDate.now())
                .build();
    }

    private PessoaInserida getPessoaInseridaFixture() {
        return PessoaInserida.builder()
                .id(1L)
                .build();
    }

    private PessoaInseridaResponse getMockPessoaInseridaResponse() {
        return PessoaInseridaResponse.builder()
                .id(1L)
                .build();
    }

    private PessoaResponse getMockPessoaResponse() {
        return PessoaResponse.builder()
                .nome("teste")
                .apelido("abc")
                .build();
    }
}