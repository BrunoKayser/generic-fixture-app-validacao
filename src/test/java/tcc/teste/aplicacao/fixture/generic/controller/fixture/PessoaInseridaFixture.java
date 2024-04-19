package tcc.teste.aplicacao.fixture.generic.controller.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;

public class PessoaInseridaFixture {
    public static PessoaInserida create() {
        return PessoaInserida.builder()
                .id(1L)
                .build();
    }
}
