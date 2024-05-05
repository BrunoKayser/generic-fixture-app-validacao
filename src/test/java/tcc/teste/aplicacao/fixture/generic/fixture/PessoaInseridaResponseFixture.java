package tcc.teste.aplicacao.fixture.generic.fixture;

import org.apache.commons.lang3.RandomUtils;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;

public class PessoaInseridaResponseFixture {

    public static PessoaInseridaResponse create() {
        return PessoaInseridaResponse.builder()
            .id(RandomUtils.nextLong())
            .build();
    }
}
