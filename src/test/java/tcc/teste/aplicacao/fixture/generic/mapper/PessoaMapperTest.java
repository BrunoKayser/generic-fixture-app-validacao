package tcc.teste.aplicacao.fixture.generic.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tcc.teste.aplicacao.fixture.generic.PessoaRequestFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PessoaMapperTest {

    @InjectMocks
    private PessoaMapper pessoaMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testToPessoa() {
        // Arrange
        PessoaRequest pessoaRequest = PessoaRequestFixture.getPessoaRequest();
        Pessoa pessoa = new Pessoa();
        when(modelMapper.map(any(PessoaRequest.class), any())).thenReturn(pessoa);

        // Act
        Pessoa result = pessoaMapper.toPessoa(pessoaRequest);

        // Assert
        assertEquals(pessoa, result);
    }

    @Test
    public void testToPessoaResponse() {
        // Arrange
        Pessoa pessoa = new Pessoa();
        PessoaResponse pessoaResponse = new PessoaResponse();
        when(modelMapper.map(any(Pessoa.class), any())).thenReturn(pessoaResponse);

        // Act
        PessoaResponse result = pessoaMapper.toPessoaResponse(pessoa);

        // Assert
        assertEquals(pessoaResponse, result);
    }

    @Test
    public void testToPessoaInseridaResponse() {
        // Arrange
        PessoaInserida pessoaInserida = new PessoaInserida();
        PessoaInseridaResponse pessoaInseridaResponse = new PessoaInseridaResponse();
        when(modelMapper.map(any(PessoaInserida.class), any())).thenReturn(pessoaInseridaResponse);

        // Act
        PessoaInseridaResponse result = pessoaMapper.toPessoaInseridaResponse(pessoaInserida);

        // Assert
        assertEquals(pessoaInseridaResponse, result);
    }
}