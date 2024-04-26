package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;

public class PessoaInseridaFixture {

    public static PessoaInserida criarPessoaInserida() {
        return PessoaInserida.builder()
                .id(1L)
                .build();
    }

}
