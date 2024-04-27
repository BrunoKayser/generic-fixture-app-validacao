package tcc.teste.aplicacao.fixture.generic.fixture;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

import java.time.LocalDate;
import java.util.List;

import static tcc.teste.aplicacao.fixture.generic.fixture.AnimaisFixture.criarAnimal;
import static tcc.teste.aplicacao.fixture.generic.fixture.AnimaisFixture.criarAnimalRequest;

public class PessoaFixture {


    public static Pessoa pessoa(){
        return Pessoa.builder().
                id(1l).
                nome("sdasdfdsa").
                apelido("dfnanfs").
                genero(GeneroEnum.MASCULINO).
                sobrenome("dhifsdhf").
                dataNascimento(LocalDate.now().minusYears(18)).
                animaisDeEstimacao(List.of(criarAnimal())).
                build();
    }

    public static PessoaRequest pessoaRequest(){
        return PessoaRequest.builder().
                dataNascimento(LocalDate.now().minusYears(18)).
                nome("sdasdfdsa").
                apelido("dfnanfs").
                genero(GeneroEnum.MASCULINO).
                sobrenome("dhifsdhf").
                animaisDeEstimacao(List.of(criarAnimalRequest())).
                build();
    }

    public static PessoaResponse pessoaResponse(){
        return PessoaResponse.builder().
                nome("sdasdfdsa").
                apelido("dfnanfs").
                genero(GeneroEnum.MASCULINO).
                sobrenome("dhifsdhf").
                build();
    }

}
