package tcc.teste.aplicacao.fixture.generic.controller.fixture;

import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;

public class PessoaInseridaResponseFixture {


    public static PessoaInseridaResponse create() {
        return PessoaInseridaResponse.builder()
                .id(1L)
                .build();
    }
}
