package tcc.teste.aplicacao.fixture.generic.response.pet.shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoPredioEnum;
import tcc.teste.aplicacao.fixture.generic.response.funcionario.FuncionarioResponse;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetShopResponse {

    private String nome;
    private String endereco;
    private TipoPredioEnum tipoPredio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInauguracao;
    private List<FuncionarioResponse> funcionarios;

}
