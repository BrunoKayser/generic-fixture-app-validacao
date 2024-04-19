package tcc.teste.aplicacao.fixture.generic.controller.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;

public class PessoaResponseFixture {
    public static PessoaResponse create() {
        return PessoaResponse.builder().
                nome("teste nome").
                apelido("teste apelido").
                sobrenome("teste sobrenome").
                genero(GeneroEnum.FEMININO).
                animaisDeEstimacao(AnimalResponseFixture.createList()).
                dataNascimento(LocalDate.now()).
                build();
    }
}
