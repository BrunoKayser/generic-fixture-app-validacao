package tcc.teste.aplicacao.fixture.generic.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoPredioEnum;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetShopRequest {

    private String nome;
    private String endereco;
    private TipoPredioEnum tipoPredio;
    private List<FuncionarioRequest> funcionarios;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInauguracao;

}
