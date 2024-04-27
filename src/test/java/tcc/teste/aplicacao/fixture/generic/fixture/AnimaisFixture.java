package tcc.teste.aplicacao.fixture.generic.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;

import java.time.LocalDate;

public class AnimaisFixture {


    public static Animal criarAnimal(){
        return Animal.builder().
                id(1l).
                dataNascimento(LocalDate.now()).
                tipoAnimal(TipoAnimalEnum.CACHORRO).
                raca("pitcher").
                build();
    }

    public static AnimalRequest criarAnimalRequest(){
        return AnimalRequest.builder().
                dataNascimento(LocalDate.now()).
                tipoAnimal(TipoAnimalEnum.CACHORRO).
                raca("pitcher").
                build();
    }

}
