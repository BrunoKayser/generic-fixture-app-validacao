package tcc.teste.aplicacao.fixture.generic.controller.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalFixture {
    public static Animal create() {
        return Animal.builder()
                .nome("Teste Nome")
                .raca("Teste raça")
                .tipoAnimal(TipoAnimalEnum.GATO)
                .dataNascimento(LocalDate.now())
                .build();
    }
    public static List<Animal> createList() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal = create();
        animalList.add(animal);
        return animalList;
    }
}
