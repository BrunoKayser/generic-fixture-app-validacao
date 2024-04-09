package tcc.teste.aplicacao.fixture.generic.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequest {

    private String nome;
    private String apelido;
    private GeneroEnum genero;
    private String sobrenome;
    private List<AnimalRequest> animaisDeEstimacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

}
