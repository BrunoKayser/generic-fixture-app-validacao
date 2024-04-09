package tcc.teste.aplicacao.fixture.generic.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponse {

    private String nome;
    private String apelido;
    private GeneroEnum genero;
    private String sobrenome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private List<AnimalResponse> animaisDeEstimacao = new ArrayList<>();

}
