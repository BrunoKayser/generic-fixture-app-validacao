package tcc.teste.aplicacao.fixture.generic;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;

import java.time.LocalDate;
import java.util.Arrays;

public class PessoaRequestFixture {

    public static PessoaRequest getPessoaRequest() {
        PessoaRequest pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("João");
        pessoaRequest.setApelido("Joca");
        pessoaRequest.setSobrenome("Silva");
        pessoaRequest.setGenero(GeneroEnum.MASCULINO);
        pessoaRequest.setAnimaisDeEstimacao(Arrays.asList(AnimalRequestFixture.getAnimalRequest(), AnimalRequestFixture.getAnimalRequest()));        pessoaRequest.setDataNascimento(LocalDate.of(1990, 1, 1));
        return pessoaRequest;
    }
}