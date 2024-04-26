package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;

import java.time.LocalDate;

public class AnimalFixture {

    public static Animal criarAnimal() {
        Animal request = new Animal();
        request.setId(1L);
        request.setNome("nome");
        request.setRaca("raca");
        request.setTipoAnimal(TipoAnimalEnum.CACHORRO);
        request.setDataNascimento(LocalDate.of(2021, 2, 2));

        return request;
    }
}
