package tcc.teste.aplicacao.fixture.generic.controller.utils;

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

public class MockUtils {

    public static PessoaRequest mockPessoaRequest() {
        return PessoaRequest.builder()
                .nome("Teste da Silva")
                .apelido("Teste")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .genero(GeneroEnum.MASCULINO)
                .animaisDeEstimacao(List.of(mockAnimalRequest()))
                .build();
    }

    public static AnimalRequest mockAnimalRequest() {
        return AnimalRequest.builder()
                .nome("Rex")
                .raca("Vira-lata")
                .dataNascimento(LocalDate.of(2010, 1, 1))
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .build();
    }

    public static Animal mockAnimal() {
        return Animal.builder()
                .id(1L)
                .nome("Rex")
                .raca("Vira-lata")
                .dataNascimento(LocalDate.of(2010, 1, 1))
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .build();
    }

    public static AnimalResponse mockAnimalResponse() {
        return AnimalResponse.builder()
                .nome("Rex")
                .raca("Vira-lata")
                .dataNascimento(LocalDate.of(2010, 1, 1))
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .build();
    }

    public static Pessoa mockPessoa() {
        return Pessoa.builder()
                .id(1L)
                .nome("Teste da Silva")
                .apelido("Teste")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .genero(GeneroEnum.MASCULINO)
                .animaisDeEstimacao(List.of(mockAnimal()))
                .build();
    }

    public static PessoaInserida mockPessoaInserida(Long id) {
        return new PessoaInserida(id);
    }

    public static PessoaInseridaResponse mockPessoaInseridaResponse(Long id) {
        return new PessoaInseridaResponse(id);
    }

    public static PessoaResponse mockPessoaResponse() {
        return PessoaResponse.builder()
                .nome("Teste da Silva")
                .apelido("Teste")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .genero(GeneroEnum.MASCULINO)
                .animaisDeEstimacao(List.of(mockAnimalResponse()))
                .build();
    }
}
