package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @InjectMocks
    PessoaController controller;

    @Mock
    InserirPessoaService inserirPessoaService;
    @Mock
    ConsultarPessoaService consultarPessoaService;
    @Mock
    PessoaMapper pessoaMapper;


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
    @DisplayName("Deve receber uma PessoaRequest e inserir")
    void testInsert() {
        when(pessoaMapper.toPessoa(any())).thenReturn(pessoa);
        when(inserirPessoaService.inserir(any())).thenReturn(pessoaInserida);
        when(pessoaMapper.toPessoaInseridaResponse(any())).thenReturn(pessoaInseridaResponse);

        var response = controller.insert(pessoaRequest);

        assertEquals(response.getBody(), pessoaInseridaResponse);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        verify(pessoaMapper, times(1)).toPessoa(any());
        verify(inserirPessoaService, times(1)).inserir(any());

    }

    @Test
    @DisplayName("Deve buscar uma pessoa por ID")
    void testGetPessoa() {
        when(pessoaMapper.toPessoaResponse(any())).thenReturn(pessoaResponse);

        var response = controller.getPessoa(1L);

        assertEquals(response.getBody(), pessoaResponse);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        verify(pessoaMapper, times(1)).toPessoaResponse(any());
    }

}