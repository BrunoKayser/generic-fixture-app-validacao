package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    void deveInserirComSucesso() {
        PessoaRequest request = getPessoaRequest();

        when(inserirPessoaService.inserir(any())).thenReturn(getPessoaInserida());
        when(pessoaMapper.toPessoaInseridaResponse(any())).thenReturn(getPessoaInseridaResponse());

        var result = assertDoesNotThrow(() -> controller.insert(request));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(Objects.requireNonNull(result.getBody()).getId());
    }

    @Test
    void deveConsultarComSucesso() {
        when(pessoaMapper.toPessoaResponse(any())).thenReturn(getPessoaResponse());

        var result = assertDoesNotThrow(() -> controller.getPessoa(1L));
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    private PessoaRequest getPessoaRequest() {
        return PessoaRequest.builder()
                .animaisDeEstimacao(emptyList())
                .nome("Lucas")
                .apelido("Loky")
                .genero(GeneroEnum.NAO_BINARIO)
                .sobrenome("Rech")
                .dataNascimento(LocalDate.of(1995, 11, 30))
                .build();
    }

    private PessoaInserida getPessoaInserida() {
        return PessoaInserida.builder()
                .id(1L)
                .build();
    }

    private PessoaInseridaResponse getPessoaInseridaResponse() {
        return PessoaInseridaResponse.builder()
                .id(1L)
                .build();
    }

    private PessoaResponse getPessoaResponse() {
        return PessoaResponse.builder()
                .animaisDeEstimacao(emptyList())
                .nome("Lucas")
                .apelido("Loky")
                .genero(GeneroEnum.NAO_BINARIO)
                .sobrenome("Rech")
                .dataNascimento(LocalDate.of(1995, 11, 30))
                .build();
    }

}