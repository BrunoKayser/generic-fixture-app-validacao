package tcc.teste.aplicacao.fixture.generic;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;

import java.time.LocalDate;

public class AnimalFixture {

    public static Animal getAnimal() {
        Animal animal = new Animal();
        animal.setNome("Rex");
        animal.setDataNascimento(LocalDate.of(2015, 1, 1));
        animal.setRaca("Labrador");
        animal.setTipoAnimal(TipoAnimalEnum.CACHORRO);
        return animal;
    }
}