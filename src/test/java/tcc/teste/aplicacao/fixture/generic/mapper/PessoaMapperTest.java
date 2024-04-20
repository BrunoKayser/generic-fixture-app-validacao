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
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;
import java.util.ArrayList;

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
    public void deveMapearPessoaRequestParaPessoaComSucesso() {
        // given
        var pessoaRequest = new PessoaRequest(
                "Nome",
                "Sobrenome",
                "Apelido",
                GeneroEnum.MASCULINO,
                null,
                LocalDate.now());

        // when
        var pessoa = new Pessoa(1L, "Nome", "Apelido", GeneroEnum.MASCULINO, "Sobrenome", LocalDate.now(), null);

        when(modelMapper.map(any(), any())).thenReturn(pessoa);

        var pessoaResult = pessoaMapper.toPessoa(pessoaRequest);

        // then
        assertNotNull(pessoa);
        assertEquals(pessoaResult.getNome(), pessoa.getNome());
        assertEquals(pessoaResult.getSobrenome(), pessoa.getSobrenome());
        assertEquals(pessoaResult.getApelido(), pessoa.getApelido());
        assertEquals(pessoaResult.getGenero(), pessoa.getGenero());
        assertEquals(pessoaResult.getDataNascimento(), pessoa.getDataNascimento());
    }

    @Test
    public void deveMapearPessoaParaPessoaResponseComSucesso() {
        // given
        var pessoa = new Pessoa(1L, "Nome", "Apelido", GeneroEnum.MASCULINO, "Sobrenome", LocalDate.now(), null);

        // when
        var pessoaResponse = new PessoaResponse("Nome", "Apelido", GeneroEnum.MASCULINO, "Sobrenome", LocalDate.now(), null);

        when(modelMapper.map(any(), any())).thenReturn(pessoaResponse);

        var pessoaResult = pessoaMapper.toPessoaResponse(pessoa);

        // then
        assertNotNull(pessoa);
        assertEquals(pessoaResult.getNome(), pessoa.getNome());
        assertEquals(pessoaResult.getSobrenome(), pessoa.getSobrenome());
        assertEquals(pessoaResult.getApelido(), pessoa.getApelido());
        assertEquals(pessoaResult.getGenero(), pessoa.getGenero());
        assertEquals(pessoaResult.getDataNascimento(), pessoa.getDataNascimento());
    }

    @Test
    public void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {
        // given
        var pessoaInserida = new PessoaInserida(1L);

        // when
        var pessoaInseridaResponse = new PessoaInseridaResponse(1L);

        when(modelMapper.map(any(), any())).thenReturn(pessoaInseridaResponse);

        var pessoaResult = pessoaMapper.toPessoaInseridaResponse(pessoaInserida);

        // then
        assertNotNull(pessoaInserida);
        assertEquals(pessoaResult.getId(), pessoaInserida.getId());
    }
}