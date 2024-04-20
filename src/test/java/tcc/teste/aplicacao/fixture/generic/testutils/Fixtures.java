package tcc.teste.aplicacao.fixture.generic.testutils;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

public class Fixtures {
    public static PessoaInserida randomPessoaInserida() {
        PessoaInserida pessoa = new PessoaInserida();

        pessoa.setId(nextLong());

        return pessoa;
    }

    public static PessoaInseridaResponse randomPessoaInseridaResponse() {
        PessoaInseridaResponse pessoa = new PessoaInseridaResponse();

        pessoa.setId(nextLong());

        return pessoa;
    }

    public static Pessoa.PessoaBuilder randomPessoaBuilder() {
        Long id = nextLong(); // Assuming the ID is within a certain range
        String nome = randomAlphabetic(12);
        String apelido = randomAlphabetic(12);
        GeneroEnum genero = GeneroEnum.values()[nextInt(0, GeneroEnum.values().length)];
        String sobrenome = randomAlphabetic(12);
        LocalDate dataNascimento = LocalDate.now().minusYears(nextInt(18, 40)); // Random age between 18 and 68

        // Generate random list of Animal objects
        List<Animal> animaisDeEstimacao = new ArrayList<>();
        int numAnimais = nextInt(1, 5); // Generate up to 5 animals
        for (int i = 0; i < numAnimais; i++) {
            Animal animal = randomAnimal();
            animaisDeEstimacao.add(animal);
        }

        // Create and return the Pessoa object
        return Pessoa.builder()
            .id(id)
            .nome(nome)
            .apelido(apelido)
            .genero(genero)
            .sobrenome(sobrenome)
            .dataNascimento(dataNascimento)
            .animaisDeEstimacao(animaisDeEstimacao);
    }

    public static Pessoa randomPessoa() {
        return randomPessoaBuilder().build();
    }

    public static Animal randomAnimal() {
        Long id = nextLong();
        String nome = "RandomAnimalName";
        LocalDate dataNascimento = LocalDate.now().minusYears(nextInt(1, 11));
        String raca = "RandomRaca";
        TipoAnimalEnum tipoAnimal = TipoAnimalEnum.values()[nextInt(1, TipoAnimalEnum.values().length)];

        return Animal.builder()
            .id(id)
            .nome(nome)
            .dataNascimento(dataNascimento)
            .raca(raca)
            .tipoAnimal(tipoAnimal)
            .build();
    }

    public static PessoaRequest randomPessoaRequest() {
        String nome = randomAlphabetic(12);
        String apelido = randomAlphabetic(12);
        String sobrenome = randomAlphabetic(12);
        GeneroEnum genero = GeneroEnum.values()[nextInt(0, GeneroEnum.values().length)];
        List<AnimalRequest> animaisDeEstimacao = randomAnimalRequestList();
        LocalDate dataNascimento = LocalDate.now().minusYears(nextInt(18, 60));

        return PessoaRequest.builder()
            .nome(nome)
            .apelido(apelido)
            .sobrenome(sobrenome)
            .genero(genero)
            .animaisDeEstimacao(animaisDeEstimacao)
            .dataNascimento(dataNascimento)
            .build();
    }

    private static List<AnimalRequest> randomAnimalRequestList() {
        List<AnimalRequest> animalRequests = new ArrayList<>();
        int numAnimais = nextInt(1, 6);
        for (int i = 0; i < numAnimais; i++) {
            AnimalRequest animalRequest = randomAnimalRequest();
            animalRequests.add(animalRequest);
        }
        return animalRequests;
    }

    public static AnimalRequest randomAnimalRequest() {
        String nome = randomAlphabetic(12);
        String raca = randomAlphabetic(12);
        TipoAnimalEnum tipoAnimal = TipoAnimalEnum.values()[nextInt(1, TipoAnimalEnum.values().length)];
        LocalDate dataNascimento = LocalDate.now().minusYears(nextInt(1, 10));

        return AnimalRequest.builder()
            .nome(nome)
            .raca(raca)
            .tipoAnimal(tipoAnimal)
            .dataNascimento(dataNascimento)
            .build();
    }

    public static PessoaResponse randomPessoaResponse() {
        String nome = "RandomNome";
        String apelido = "RandomApelido";
        GeneroEnum genero = GeneroEnum.values()[nextInt(1, GeneroEnum.values().length)];
        String sobrenome = "RandomSobrenome";
        LocalDate dataNascimento = LocalDate.now().minusYears(nextInt(18, 50));
        List<AnimalResponse> animaisDeEstimacao = randomAnimalResponseList();

        return PessoaResponse.builder()
            .nome(nome)
            .apelido(apelido)
            .genero(genero)
            .sobrenome(sobrenome)
            .dataNascimento(dataNascimento)
            .animaisDeEstimacao(animaisDeEstimacao)
            .build();
    }

    private static List<AnimalResponse> randomAnimalResponseList() {
        List<AnimalResponse> animalResponses = new ArrayList<>();

        int numAnimais = nextInt(1, 6); // Generate up to 5 animals

        for (int i = 0; i < numAnimais; i++) {

            animalResponses.add(randomAnimalResponse());
        }

        return animalResponses;
    }

    public static AnimalResponse randomAnimalResponse() {
        String nome = randomAlphabetic(12);
        String raca = randomAlphabetic(12);
        TipoAnimalEnum tipoAnimal = TipoAnimalEnum.values()[nextInt(1, TipoAnimalEnum.values().length)];
        LocalDate dataNascimento = LocalDate.now().minusYears(nextInt(1, 11));

        return AnimalResponse.builder()
            .nome(nome)
            .raca(raca)
            .tipoAnimal(tipoAnimal)
            .dataNascimento(dataNascimento)
            .build();
    }
}