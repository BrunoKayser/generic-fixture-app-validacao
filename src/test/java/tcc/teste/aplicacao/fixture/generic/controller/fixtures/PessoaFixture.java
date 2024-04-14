package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaFixture {
    public static Pessoa pessoa() {
        return Pessoa.builder()
                .nome("João")
                .apelido("Joãozinho")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();
    }

    public static Pessoa pessoaComAnimal(int quantidade) {
        Pessoa pessoa = pessoa();
        List<Animal> animalList = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            Animal animal = animal();
            animal.setPessoa(pessoa);
            animalList.add(animal);
        }

        pessoa.setAnimaisDeEstimacao(animalList);
        return pessoa;
    }

    public static Animal animal() {
        return Animal.builder()
                .nome("Rex")
                .raca("Spitz")
                .raca("Vira-lata")
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .build();
    }
}
