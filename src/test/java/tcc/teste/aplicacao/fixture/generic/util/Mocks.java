package tcc.teste.aplicacao.fixture.generic.util;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mocks {

    public static PessoaRequest gerarPessoaRequest() {
        return PessoaRequest.builder()
                .nome("Nome")
                .apelido("Apelido")
                .sobrenome("Sobrenome")
                .dataNascimento(LocalDate.now())
                .genero(GeneroEnum.MASCULINO)
                .animaisDeEstimacao(List.of(gerarAnimalRequest()))
                .build();
    }

    public static AnimalRequest gerarAnimalRequest() {
        return AnimalRequest.builder()
                .nome("Nome")
                .raca("Raca")
                .dataNascimento(LocalDate.now())
                .tipoAnimal(null)
                .build();
    }

    public static Pessoa gerarPessoa() {
        Pessoa build = Pessoa.builder()
                .nome("Nome")
                .apelido("Apelido")
                .sobrenome("Sobrenome")
                .dataNascimento(LocalDate.now())
                .genero(GeneroEnum.MASCULINO)
                .animaisDeEstimacao(new ArrayList<>())
                .build();
        build.getAnimaisDeEstimacao().add(gerarAnimal());
        return build;
    }

    public static Animal gerarAnimal() {
        return Animal.builder()
                .nome("Nome")
                .raca("Raca")
                .dataNascimento(LocalDate.now())
                .tipoAnimal(null)
                .build();
    }

    public static PessoaInserida gerarPessoaInserida() {
        return new PessoaInserida(1L);
    }

    public static PessoaInseridaResponse gerarPessoaInseridaResponse() {
        return new PessoaInseridaResponse(1L);
    }

    public static PessoaResponse gerarPessoaResponse() {
        return PessoaResponse.builder()
                .nome("Nome")
                .apelido("Apelido")
                .sobrenome("Sobrenome")
                .dataNascimento(LocalDate.now())
                .genero(GeneroEnum.MASCULINO)
                .animaisDeEstimacao(List.of(gerarAnimalResponse()))
                .build();
    }

    public static AnimalResponse gerarAnimalResponse() {
        return AnimalResponse.builder()
                .nome("Nome")
                .raca("Raca")
                .dataNascimento(LocalDate.now())
                .tipoAnimal(null)
                .build();
    }
}
