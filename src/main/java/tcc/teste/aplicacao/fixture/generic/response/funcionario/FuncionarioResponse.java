package tcc.teste.aplicacao.fixture.generic.response.funcionario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioResponse {

    private String nome;
    private String sobrenome;
    private GeneroEnum genero;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

}
