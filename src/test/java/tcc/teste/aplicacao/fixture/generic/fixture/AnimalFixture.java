package tcc.teste.aplicacao.fixture.generic.fixture;

import java.time.LocalDate;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;

public class AnimalFixture {

    public static Animal create() {
        return Animal.builder()
            .id(RandomUtils.nextLong())
            .nome(RandomStringUtils.randomAlphabetic(10))
            .raca(RandomStringUtils.randomAlphabetic(10))
            .tipoAnimal(TipoAnimalEnum.OUTRO_TIPO)
            .dataNascimento(LocalDate.now())
            .build();
    }
}
