package tcc.teste.aplicacao.fixture.generic.fixture;

import java.time.LocalDate;
import java.util.Collections;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;

public class PessoaFixture {


    public static Pessoa create() {
        return Pessoa.builder()
            .id(RandomUtils.nextLong())
            .nome(RandomStringUtils.randomAlphabetic(10))
            .apelido(RandomStringUtils.randomAlphabetic(10))
            .sobrenome(RandomStringUtils.randomAlphabetic(10))
            .genero(GeneroEnum.NAO_BINARIO)
            .animaisDeEstimacao(Collections.singletonList(AnimalFixture.create()))
            .dataNascimento(LocalDate.now())
            .build();


    }
}
