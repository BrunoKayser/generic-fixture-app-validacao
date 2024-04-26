package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;
import java.util.List;

public class PessoaResponseFixture {

    public static PessoaResponse criarPessoaResponse() {
        return PessoaResponse.builder()
                .nome("nome")
                .apelido("apelido")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("sobrenome")
                .dataNascimento(LocalDate.of(2021, 2, 2))
                .animaisDeEstimacao(List.of(AnimalResponseFixture.criarAnimal()))
                .build();
    }
}
