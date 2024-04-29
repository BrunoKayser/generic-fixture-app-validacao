package tcc.teste.aplicacao.fixture.generic.controller.Fixture;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PessoaRequestFixture {

    public static PessoaRequest criaPessoa() {
        return new PessoaRequest("Mariele",
                "Mari",
                "Santos",
                GeneroEnum.FEMININO,
                criaAnimal(),
                LocalDate.now()
        );
    }

    private static List<AnimalRequest> criaAnimal() {
        AnimalRequest animalRequest1 = new AnimalRequest("Jack",
                "shitzu",
                TipoAnimalEnum.CACHORRO,
                LocalDate.now());
        AnimalRequest animalRequest2 = new AnimalRequest("Thor",
                "poodle",
                TipoAnimalEnum.CACHORRO,
                LocalDate.now());

        return Arrays.asList(animalRequest1, animalRequest2);
    }

}
