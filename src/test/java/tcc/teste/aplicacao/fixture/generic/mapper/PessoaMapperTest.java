package tcc.teste.aplicacao.fixture.generic.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {

    @InjectMocks
    private PessoaMapper pessoaMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void deveMapearPessoaRequestParaPessoaComSucesso() {
        when(modelMapper.map(any(), any())).thenReturn(getPessoa());

        var result = assertDoesNotThrow(() -> pessoaMapper.toPessoa(getPessoaRequest()));

        assertNotNull(result.getId());
        assertNotNull(result.getAnimaisDeEstimacao());
        assertNotNull(result.getNome());
        assertNotNull(result.getApelido());
        assertNotNull(result.getGenero());
        assertNotNull(result.getSobrenome());
        assertNotNull(result.getDataNascimento());
    }

    @Test
    void deveMapearPessoaParaPessoaResponseComSucesso() {
        when(modelMapper.map(any(), any())).thenReturn(getPessoaResponse());

        var result = assertDoesNotThrow(() -> pessoaMapper.toPessoaResponse(getPessoa()));

        assertNotNull(result.getAnimaisDeEstimacao());
        assertNotNull(result.getNome());
        assertNotNull(result.getApelido());
        assertNotNull(result.getGenero());
        assertNotNull(result.getSobrenome());
        assertNotNull(result.getDataNascimento());
    }

    @Test
    void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {
        when(modelMapper.map(any(), any())).thenReturn(getPessoaInseridaResponse());

        var result = assertDoesNotThrow(() -> pessoaMapper.toPessoaInseridaResponse(getPessoaInserida()));

        assertNotNull(result.getId());
    }

    private Pessoa getPessoa() {
        return Pessoa.builder()
                .id(1L)
                .animaisDeEstimacao(emptyList())
                .nome("Lucas")
                .apelido("Loky")
                .genero(GeneroEnum.NAO_BINARIO)
                .sobrenome("Rech")
                .dataNascimento(LocalDate.of(1995, 11, 30))
                .build();
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

    private PessoaInseridaResponse getPessoaInseridaResponse() {
        return PessoaInseridaResponse.builder()
                .id(1L)
                .build();
    }

    private PessoaInserida getPessoaInserida() {
        return PessoaInserida.builder()
                .id(1L)
                .build();
    }

}