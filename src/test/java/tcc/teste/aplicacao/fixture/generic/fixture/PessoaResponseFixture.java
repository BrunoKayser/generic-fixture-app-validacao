package tcc.teste.aplicacao.fixture.generic.fixture;

import java.time.LocalDate;
import java.util.Collections;
import org.apache.commons.lang3.RandomStringUtils;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

public class PessoaResponseFixture {

    public static PessoaResponse create() {
        return PessoaResponse.builder()
            .nome(RandomStringUtils.randomAlphabetic(10))
            .apelido(RandomStringUtils.randomAlphabetic(10))
            .sobrenome(RandomStringUtils.randomAlphabetic(10))
            .genero(GeneroEnum.NAO_BINARIO)
            .animaisDeEstimacao(Collections.singletonList(AnimalResponseFixture.create()))
            .dataNascimento(LocalDate.now())
            .build();
    }
}
