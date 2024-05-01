package tcc.teste.aplicacao.fixture.generic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {

    @InjectMocks
    private PessoaMapper pessoaMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void toPessoa() {
        Pessoa pessoa = getPessoaFixture();
        PessoaRequest pessoaRequest = getPessoaRequestFixture();

        doReturn(pessoa).when(modelMapper).map(pessoaRequest, Pessoa.class);

        Pessoa result = pessoaMapper.toPessoa(pessoaRequest);

        Assertions.assertEquals(pessoa, result);
    }

    @Test
    void toPessoaResponse() {
        Pessoa pessoa = getPessoaFixture();
        PessoaResponse pessoaResponse = getMockPessoaResponse();

        doReturn(pessoaResponse).when(modelMapper).map(pessoa, PessoaResponse.class);

        PessoaResponse result = pessoaMapper.toPessoaResponse(pessoa);
    }

    @Test
    void toPessoaInseridaResponse() {
        PessoaInserida pessoaInserida = getPessoaInseridaFixture();
        PessoaInseridaResponse pessoaInseridaResponse = getMockPessoaInseridaResponse();

        doReturn(pessoaInseridaResponse).when(modelMapper).map(pessoaInserida, PessoaInseridaResponse.class);

        PessoaInseridaResponse result = pessoaMapper.toPessoaInseridaResponse(pessoaInserida);

        Assertions.assertEquals(pessoaInseridaResponse, result);
    }

    private Pessoa getPessoaFixture() {
        return Pessoa.builder()
                .nome("teste")
                .apelido("abc")
                .dataNascimento(LocalDate.now())
                .build();
    }


    private PessoaRequest getPessoaRequestFixture() {
        return PessoaRequest.builder()
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