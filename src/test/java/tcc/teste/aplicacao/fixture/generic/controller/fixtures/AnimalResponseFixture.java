package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;

import java.time.LocalDate;

public class AnimalResponseFixture {

    public static AnimalResponse criarAnimal() {
        return AnimalResponse.builder()
                .nome("nome")
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .raca("raca")
                .dataNascimento(LocalDate.of(2021, 2, 2))
                .build();
    }
}