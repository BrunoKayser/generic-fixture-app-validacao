package tcc.teste.aplicacao.fixture.generic.fixture;

import java.time.LocalDate;
import org.apache.commons.lang3.RandomStringUtils;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;

public class AnimalResponseFixture {

    public static AnimalResponse create() {
        return AnimalResponse.builder()
            .nome(RandomStringUtils.randomAlphabetic(10))
            .raca(RandomStringUtils.randomAlphabetic(10))
            .tipoAnimal(TipoAnimalEnum.OUTRO_TIPO)
            .dataNascimento(LocalDate.now())
            .build();
    }
}
