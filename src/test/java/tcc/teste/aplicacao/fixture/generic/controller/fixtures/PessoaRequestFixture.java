package tcc.teste.aplicacao.fixture.generic.controller.fixtures;

import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaRequestFixture {
    public static PessoaRequest pessoaRequest() {
        return PessoaRequest.builder()
                .nome("João")
                .apelido("Joãozinho")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();
    }

    public static PessoaRequest pessoaRequestComAnimais(int quantidade) {
        PessoaRequest pessoaRequest = pessoaRequest();
        List<AnimalRequest> animais = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            animais.add(animalRequest());
        }

        pessoaRequest.setAnimaisDeEstimacao(animais);
        return pessoaRequest;
    }

    public static AnimalRequest animalRequest() {
        return AnimalRequest.builder()
                .nome("Rex")
                .raca("Spitz")
                .raca("Vira-lata")
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .build();
    }
}
