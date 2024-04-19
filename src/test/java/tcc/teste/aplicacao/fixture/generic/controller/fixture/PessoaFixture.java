package tcc.teste.aplicacao.fixture.generic.controller.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;

import java.time.LocalDate;

public class PessoaFixture {
    public static Pessoa create() {
        return Pessoa.builder().
                nome("teste nome").
                id(1L).
                apelido("teste apelido").
                sobrenome("teste sobrenome").
                genero(GeneroEnum.FEMININO).
                animaisDeEstimacao(AnimalFixture.createList()).
                dataNascimento(LocalDate.now()).
                build();
    }
}
