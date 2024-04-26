package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PessoaFixture {

    public static Pessoa criarPessoa() {
        Pessoa request = new Pessoa();
        request.setId(1L);
        request.setApelido("apelido");
        request.setNome("nome");
        request.setSobrenome("sobrenome");
        request.setGenero(GeneroEnum.MASCULINO);
        request.setAnimaisDeEstimacao(Collections.singletonList(AnimalFixture.criarAnimal()));
        request.setDataNascimento(LocalDate.of(2021, 2, 2));

        return request;
    }
}
