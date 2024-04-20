package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MockObjects {
    public static final AnimalRequest animalRequest = new AnimalRequest(
            "Nome",
            "Raca",
            TipoAnimalEnum.GATO,
            LocalDate.now()
    );

    public static final PessoaRequest pessoaRequest = new PessoaRequest(
            "Nome",
            "Sobrenome",
            "Apelido",
            GeneroEnum.MASCULINO,
            new ArrayList<>(),
            LocalDate.now()
    );

    public static final Pessoa pessoa = new Pessoa(
            1L,
            "Nome",
            "Sobrenome",
            GeneroEnum.MASCULINO,
            "Apelido",
            LocalDate.now(),
            new ArrayList<>()
    );

    public static final Animal animal = new Animal(
            1L,
            "Nome",
            LocalDate.now(),
            "Raca",
            TipoAnimalEnum.GATO,
            pessoa
    );

    public static final PessoaInserida pessoaInserida = new PessoaInserida(1L);

    public static final PessoaInseridaResponse pessoaInseridaResponse = new PessoaInseridaResponse(1L);

}

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {
    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private InserirPessoaService inserirPessoaService;

    @Mock
    private ConsultarPessoaService consultarPessoaService;

    @Mock
    private PessoaMapper pessoaMapper;



    @Test
    public void deveInserirComSucesso() {
        // Arrange
        var animalList = new ArrayList<AnimalRequest>();

        var nascimentoAnimal = LocalDate.now();

        var animalRequest = new AnimalRequest(
                "Nome",
                "Raca",
                TipoAnimalEnum.GATO,
                nascimentoAnimal
        );

        animalList.add(animalRequest);

        var nascimentoPessoa = LocalDate.now();

        var pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("Nome");
        pessoaRequest.setSobrenome("Sobrenome");
        pessoaRequest.setApelido("Apelido");
        pessoaRequest.setGenero(GeneroEnum.MASCULINO);
        pessoaRequest.setDataNascimento(nascimentoPessoa);
        pessoaRequest.setAnimaisDeEstimacao(animalList);

        // Act
        pessoaController.insert(pessoaRequest);

        var resultAnimalList = new ArrayList<Animal>();

        var resultPessoa = new Pessoa(
                1L,
                "Nome",
                "Sobrenome",
                GeneroEnum.MASCULINO,
                "Apelido",
                nascimentoPessoa,
                resultAnimalList
        );


        var resultAnimal = new Animal(
                1L,
                "Nome",
                nascimentoAnimal,
                "Raça",
                TipoAnimalEnum.GATO,
                resultPessoa
        );

        PessoaInserida pessoaInserida = new PessoaInserida(1L);

        resultAnimalList.add(resultAnimal);

        when(inserirPessoaService.inserir(any())).thenReturn(pessoaInserida);
        when(pessoaMapper.toPessoa(pessoaRequest)).thenReturn(resultPessoa);
        when(pessoaMapper.toPessoaInseridaResponse(pessoaInserida)).thenReturn(new PessoaInseridaResponse(1L));

        // Assert
        var response = pessoaController.insert(pessoaRequest);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void deveConsultarComSucesso() {
        // Arrange
        var nascimentoPessoa = LocalDate.now();

        var pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("Nome");
        pessoaRequest.setSobrenome("Sobrenome");
        pessoaRequest.setApelido("Apelido");
        pessoaRequest.setGenero(GeneroEnum.MASCULINO);
        pessoaRequest.setDataNascimento(nascimentoPessoa);

        var resultAnimalList = new ArrayList<Animal>();

        var resultPessoa = new Pessoa(
                1L,
                "Nome",
                "Sobrenome",
                GeneroEnum.MASCULINO,
                "Apelido",
                nascimentoPessoa,
                resultAnimalList
        );

        var resultAnimal = new Animal(
                1L,
                "Nome",
                LocalDate.now(),
                "Raça",
                TipoAnimalEnum.GATO,
                resultPessoa
        );

        resultAnimalList.add(resultAnimal);

        when(consultarPessoaService.consultar(1L)).thenReturn(resultPessoa);
        when(pessoaMapper.toPessoaResponse(resultPessoa)).thenReturn(new PessoaResponse("Nome", "Apelido", GeneroEnum.MASCULINO, "Sobrenome", nascimentoPessoa, new ArrayList<>()));

        // Act
        var response = pessoaController.getPessoa(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Nome", response.getBody().getNome());
        assertEquals("Sobrenome", response.getBody().getSobrenome());
        assertEquals(GeneroEnum.MASCULINO, response.getBody().getGenero());
        assertEquals("Apelido", response.getBody().getApelido());
        assertEquals(nascimentoPessoa, response.getBody().getDataNascimento());
    }
}