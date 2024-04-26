package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;

import java.time.LocalDate;

public class AnimalRequestFixture {

    public static AnimalRequest criarAnimal() {
        AnimalRequest request = new AnimalRequest();
        request.setNome("nome");
        request.setRaca("raca");
        request.setTipoAnimal(TipoAnimalEnum.CACHORRO);
        request.setDataNascimento(LocalDate.of(2021, 2, 2));

        return request;
    }
}
