package tcc.teste.aplicacao.fixture.generic;

import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;

import java.time.LocalDate;

public class AnimalRequestFixture {

    public static AnimalRequest getAnimalRequest() {
        AnimalRequest animalRequest = new AnimalRequest();
        animalRequest.setNome("Rex");
        animalRequest.setRaca("Labrador");
        animalRequest.setTipoAnimal(TipoAnimalEnum.CACHORRO);
        animalRequest.setDataNascimento(LocalDate.of(2015, 1, 1));
        return animalRequest;
    }
}