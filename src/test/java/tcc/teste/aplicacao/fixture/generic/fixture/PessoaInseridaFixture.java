package tcc.teste.aplicacao.fixture.generic.fixture;

import org.apache.commons.lang3.RandomUtils;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;

public class PessoaInseridaFixture {


    public static PessoaInserida create() {
        return PessoaInserida.builder()
            .id(RandomUtils.nextLong())
            .build();
    }
}
