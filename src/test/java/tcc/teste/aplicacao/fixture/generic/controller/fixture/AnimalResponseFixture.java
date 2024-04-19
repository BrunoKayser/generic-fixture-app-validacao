package tcc.teste.aplicacao.fixture.generic.controller.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalResponseFixture {
    public static AnimalResponse create() {
        return AnimalResponse.builder()
                .nome("Teste Nome")
                .raca("Teste raça")
                .tipoAnimal(TipoAnimalEnum.GATO)
                .dataNascimento(LocalDate.now())
                .build();
    }
    public static List<AnimalResponse> createList() {
        List<AnimalResponse> animalList = new ArrayList<>();
        AnimalResponse animalResponse = create();
        animalList.add(animalResponse);
        return animalList;
    }
}
