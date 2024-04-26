package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;

public class PessoaInseridaResponseFixture {

    public static PessoaInseridaResponse criarPessoaInseridaResponse() {
        return PessoaInseridaResponse
                .builder()
                .id(1L)
                .build();
    }
}
