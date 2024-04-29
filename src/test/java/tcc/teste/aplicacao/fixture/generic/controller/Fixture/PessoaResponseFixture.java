package tcc.teste.aplicacao.fixture.generic.controller.Fixture;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PessoaResponseFixture {

    public static PessoaResponse criaPessoa() {
        return new PessoaResponse("Mariele",
                "Mari",
                GeneroEnum.FEMININO,
                "Santos",
                LocalDate.now(),
                criaAnimal()
                );
    }

    private static List<AnimalResponse> criaAnimal() {
        AnimalResponse animalResponse1 = new AnimalResponse("Jack",
                "shitzu",
                TipoAnimalEnum.CACHORRO,
                LocalDate.now());

        AnimalResponse animalResponse2 = new AnimalResponse("Thor",
                "poodle",
                TipoAnimalEnum.CACHORRO,
                LocalDate.now());

        return Arrays.asList(animalResponse1, animalResponse2);
    }

}
