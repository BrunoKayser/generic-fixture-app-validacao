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
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @Mock
    private InserirPessoaService inserirPessoaService;
    @Mock
    private ConsultarPessoaService consultarPessoaService;
    @Mock
    private PessoaMapper pessoaMapper;
    @InjectMocks
    private PessoaController pessoaController;


    @Test
    void insert() {
        var animalRequest = AnimalRequest.builder()
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .nome("Meg")
                .nome("Viralata")
                .dataNascimento(LocalDate.of(2023, 1, 4))
                .build();

        var pessoaRequest = PessoaRequest.builder()
                .nome("Lucas")
                .sobrenome("de Leão")
                .animaisDeEstimacao(List.of(animalRequest))
                .apelido("Marreco")
                .dataNascimento(LocalDate.of(1994, 12, 29))
                .genero(GeneroEnum.MASCULINO)
                .build();

        Pessoa pessoa = Pessoa.builder()
                .id(1L)
                .nome("João")
                .apelido("Joãozinho")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1990, 5, 15))
                .animaisDeEstimacao(Arrays.asList(
                        Animal.builder()
                                .id(1L)
                                .nome("Rex")
                                .dataNascimento(LocalDate.of(2015, 3, 10))
                                .raca("Labrador")
                                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                                .build(),
                        Animal.builder()
                                .id(2L)
                                .nome("Mia")
                                .dataNascimento(LocalDate.of(2018, 7, 20))
                                .raca("Persa")
                                .tipoAnimal(TipoAnimalEnum.GATO)
                                .build()
                ))
                .build();



        var pessoaInserida = PessoaInserida.builder().id(1L).build();
        var pessoaInseridaResponse = PessoaInseridaResponse.builder().id(1L).build();


        when(pessoaMapper.toPessoa(pessoaRequest)).thenReturn(pessoa);
        when(inserirPessoaService.inserir(pessoa)).thenReturn(pessoaInserida);
        when(pessoaMapper.toPessoaInseridaResponse(pessoaInserida)).thenReturn(pessoaInseridaResponse);

        var pessoaResponseActual =  pessoaController.insert(pessoaRequest);

        assertEquals(pessoaInseridaResponse, pessoaResponseActual.getBody());

    }

    @Test
    void getPessoa() {

        var animalResponse = AnimalResponse.builder()
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .nome("Meg")
                .nome("Viralata")
                .dataNascimento(LocalDate.of(2023, 1, 4))
                .build();

        var pessoaResponse = PessoaResponse.builder()
                .nome("João")
                .apelido("Joãozinho")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1990, 5, 15))
                .animaisDeEstimacao(List.of(animalResponse))
                .build();

        Pessoa pessoa = Pessoa.builder()
                .id(1L)
                .nome("João")
                .apelido("Joãozinho")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1990, 5, 15))
                .animaisDeEstimacao(Arrays.asList(
                        Animal.builder()
                                .id(1L)
                                .nome("Rex")
                                .dataNascimento(LocalDate.of(2015, 3, 10))
                                .raca("Labrador")
                                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                                .build(),
                        Animal.builder()
                                .id(2L)
                                .nome("Mia")
                                .dataNascimento(LocalDate.of(2018, 7, 20))
                                .raca("Persa")
                                .tipoAnimal(TipoAnimalEnum.GATO)
                                .build()
                ))
                .build();

        when(pessoaMapper.toPessoaResponse(pessoa)).thenReturn(pessoaResponse);
        when(consultarPessoaService.consultar(1L)).thenReturn(pessoa);

        var actual = pessoaController.getPessoa(1L);

        assertEquals(actual.getBody(), pessoaResponse);

    }
}