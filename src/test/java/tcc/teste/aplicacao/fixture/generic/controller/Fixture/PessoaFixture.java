package tcc.teste.aplicacao.fixture.generic.controller.Fixture;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PessoaFixture {

    public static Pessoa criaPessoa() {
        return new Pessoa(1L,
                "Mariele",
                "Mari",
                GeneroEnum.FEMININO,
                "Santos",
                LocalDate.now(),
                criaAnimal()
                );
    }

    public static Pessoa criaPessoaCom3Animais() {
        return new Pessoa(1L,
                "Mariele",
                "Mari",
                GeneroEnum.FEMININO,
                "Santos",
                LocalDate.now(),
                cria3Animais()
                );
    }

    private static List<Animal> criaAnimal() {
        Animal animal1 = new Animal(1L,
                "Jack",
                LocalDate.now(),
                "shitzu",
                TipoAnimalEnum.CACHORRO,
                null);

        Animal animal2 = new Animal(2L,
                "Thor",
                LocalDate.now(),
                "poodle",
                TipoAnimalEnum.CACHORRO,
                null);

        return Arrays.asList(animal1, animal2);
    }

    private static List<Animal> cria3Animais() {
        Animal animal1 = new Animal(1L,
                "Jack",
                LocalDate.now(),
                "shitzu",
                TipoAnimalEnum.CACHORRO,
                null);

        Animal animal2 = new Animal(2L,
                "Thor",
                LocalDate.now(),
                "poodle",
                TipoAnimalEnum.CACHORRO,
                null);

        Animal animal3 = new Animal(2L,
                "Thor",
                LocalDate.now(),
                "poodle",
                TipoAnimalEnum.CACHORRO,
                null);

        return Arrays.asList(animal1, animal2, animal3);
    }

}
