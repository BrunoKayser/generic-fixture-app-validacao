package tcc.teste.aplicacao.fixture.generic.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaMapperTest {

    @InjectMocks
    PessoaMapper mapper;

    @Mock
    ModelMapper modelMapper;

    PessoaRequest pessoaRequest;

    Pessoa pessoa;

    PessoaResponse pessoaResponse;
    PessoaInserida pessoaInserida;
    PessoaInseridaResponse pessoaInseridaResponse;

    @BeforeEach
    void init() {
        pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("Nome");
        pessoaRequest.setApelido("Apelido");
        pessoaRequest.setSobrenome("Sobrenome");
        pessoaRequest.setGenero(GeneroEnum.MASCULINO);
        pessoaRequest.setDataNascimento(LocalDate.now());
        pessoaRequest.setAnimaisDeEstimacao(List.of(new AnimalRequest("Rex", "Caramelo", TipoAnimalEnum.CACHORRO, LocalDate.now())));
        pessoaRequest = new PessoaRequest();

        pessoa = new Pessoa();
        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setSobrenome("Sobrenome");
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setAnimaisDeEstimacao(List.of(new Animal(1L, "Rex", LocalDate.now(), "Caramelo", TipoAnimalEnum.CACHORRO, null)));

        pessoaResponse = new PessoaResponse();
        pessoaResponse.setNome("Nome");
        pessoaResponse.setApelido("Apelido");
        pessoaResponse.setSobrenome("Sobrenome");
        pessoaResponse.setGenero(GeneroEnum.MASCULINO);
        pessoaResponse.setDataNascimento(LocalDate.now());
        pessoaResponse.setAnimaisDeEstimacao(List.of(new AnimalResponse("Rex", "Caramelo", TipoAnimalEnum.CACHORRO, LocalDate.now())));

        pessoaInserida = new PessoaInserida();
        pessoaInserida.setId(1L);

        pessoaInseridaResponse = new PessoaInseridaResponse();
        pessoaInseridaResponse.setId(1L);

    }

    @Test
    @DisplayName("Deve mapear PessoaInserida para PessoaInseridaResponse")
    void testToPessoaInseridaMapper() {
        when(modelMapper.map(any(), any())).thenReturn(pessoaInseridaResponse);

        var pessoaResponseMapper = mapper.toPessoaInseridaResponse(pessoaInserida);

        assertEquals(pessoaInseridaResponse, pessoaResponseMapper);

    }

    @Test
    @DisplayName("Deve mapear de pessoa para pessoaResponse")
    void testToPessoaResponseMapper() {
        when(modelMapper.map(any(), any())).thenReturn(pessoaResponse);

        var pessoaResponseMapper = mapper.toPessoaResponse(pessoa);

        assertEquals(pessoaResponse, pessoaResponseMapper);

    }

    @Test
    @DisplayName("Deve mapear de pessoa request para pessoa")
    void testToPessoaMapper() {
        when(modelMapper.map(any(), any())).thenReturn(pessoa);

        var pessoaMapperResponse = mapper.toPessoa(pessoaRequest);

        assertEquals(pessoa, pessoaMapperResponse);

    }


}