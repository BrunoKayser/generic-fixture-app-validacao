package tcc.teste.aplicacao.fixture.generic.mapper;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.PessoaResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {

    @InjectMocks
    PessoaMapper pessoaMapper;

    @Mock
    ModelMapper modelMapper;

    @Test
    void deveMapearPessoaRequestParaPessoaComSucesso() {

        var pessoaRequest = GenericFixture.generate(PessoaRequest.class);
        var pessoa = GenericFixture.generate(Pessoa.class);

        when(modelMapper.map(pessoaRequest, Pessoa.class)).thenReturn(pessoa);

        var pessoaRetorno = pessoaMapper.toPessoa(pessoaRequest);

        assertEquals(pessoaRetorno, pessoa);

    }

    @Test
    void deveMapearPessoaParaPessoaResponseComSucesso() {

        var pessoa = GenericFixture.generate(Pessoa.class);
        var pessoaResponse = GenericFixture.generate(PessoaResponse.class);

        when(modelMapper.map(pessoa, PessoaResponse.class)).thenReturn(pessoaResponse);

        var pessoaRetorno = pessoaMapper.toPessoaResponse(pessoa);

        assertEquals(pessoaRetorno, pessoaResponse);

    }

    @Test
    void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {

        var pessoaInserida = GenericFixture.generate(PessoaInserida.class);
        var pessoaInseridaResponse = GenericFixture.generate(PessoaInseridaResponse.class);

        when(modelMapper.map(pessoaInserida, PessoaInseridaResponse.class)).thenReturn(pessoaInseridaResponse);

        var pessoaRetorno = pessoaMapper.toPessoaInseridaResponse(pessoaInserida);

        assertEquals(pessoaRetorno, pessoaInseridaResponse);

    }

}