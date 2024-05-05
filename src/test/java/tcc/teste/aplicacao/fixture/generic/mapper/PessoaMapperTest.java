package tcc.teste.aplicacao.fixture.generic.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaInseridaFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaInseridaResponseFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaRequestFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaResponseFixture;

@ExtendWith(MockitoExtension.class)
public class PessoaMapperTest {

    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    PessoaMapper pessoaMapper;

    @Test
    public void toPessoaTest() {
        var pessoa = PessoaFixture.create();
        when(modelMapper.map(any(), any())).thenReturn(pessoa);
        Pessoa result = pessoaMapper.toPessoa(PessoaRequestFixture.create());
        assertEquals(pessoa, result);
    }

    @Test
    public void toPessoaResponseTest() {
        var request = PessoaResponseFixture.create();
        when(modelMapper.map(any(), any())).thenReturn(request);
        var result = pessoaMapper.toPessoaResponse(PessoaFixture.create());
        assertEquals(request, result);
    }

    @Test
    public void toPessoaInseridaResponseTest() {
        var request = PessoaInseridaResponseFixture.create();
        when(modelMapper.map(any(), any())).thenReturn(request);
        var result = pessoaMapper.toPessoaInseridaResponse(PessoaInseridaFixture.create());
        assertEquals(request, result);
    }
}
