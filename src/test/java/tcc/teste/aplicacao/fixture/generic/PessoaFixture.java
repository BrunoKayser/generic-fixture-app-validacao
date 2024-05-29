package tcc.teste.aplicacao.fixture.generic;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;

import java.time.LocalDate;
import java.util.Arrays;

public class PessoaFixture {

    public static Pessoa getPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setApelido("Joca");
        pessoa.setSobrenome("Silva");
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));
        // Definindo a lista de animais de estimação
        Animal animal1 = AnimalFixture.getAnimal();
        Animal animal2 = AnimalFixture.getAnimal();
        // Certifique-se de que a classe AnimalFixture está definida corretamente no seu código
        pessoa.setAnimaisDeEstimacao(Arrays.asList(animal1, animal2));
        return pessoa;
    }
}