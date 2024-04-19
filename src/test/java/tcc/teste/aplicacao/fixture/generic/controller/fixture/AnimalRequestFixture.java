package tcc.teste.aplicacao.fixture.generic.controller.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalRequestFixture {
    public static AnimalRequest create() {
        return AnimalRequest.builder()
                .nome("Teste Nome")
                .raca("Teste raça")
                .tipoAnimal(TipoAnimalEnum.GATO)
                .dataNascimento(LocalDate.now())
                .build();
    }
    public static List<AnimalRequest> createList() {
        List<AnimalRequest> animalRequestList = new ArrayList<>();
        AnimalRequest animalRequest = create();
        animalRequestList.add(animalRequest);
        return animalRequestList;
    }
}
