package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;

import java.time.LocalDate;
import java.util.List;

public class PessoaRequestFixture {

    public static PessoaRequest criarPessoaRequest() {
        PessoaRequest request = new PessoaRequest();
        request.setApelido("apelido");
        request.setNome("nome");
        request.setSobrenome("sobrenome");
        request.setGenero(GeneroEnum.MASCULINO);
        request.setAnimaisDeEstimacao(List.of(AnimalRequestFixture.criarAnimal()));
        request.setDataNascimento(LocalDate.of(2021, 2, 2));

        return request;
    }
}
