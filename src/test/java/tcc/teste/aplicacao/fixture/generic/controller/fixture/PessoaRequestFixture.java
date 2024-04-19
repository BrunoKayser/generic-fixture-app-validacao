package tcc.teste.aplicacao.fixture.generic.controller.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;

import java.time.LocalDate;

public class PessoaRequestFixture {

    public static PessoaRequest create() {
        return PessoaRequest.builder().
                nome("teste nome").
                apelido("teste apelido").
                sobrenome("teste sobrenome").
                genero(GeneroEnum.FEMININO).
                animaisDeEstimacao(AnimalRequestFixture.createList()).
                dataNascimento(LocalDate.now()).
                build();
    }
}
