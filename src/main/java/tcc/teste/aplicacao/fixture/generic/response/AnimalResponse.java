package tcc.teste.aplicacao.fixture.generic.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponse {

    private String nome;
    private String raca;
    private TipoAnimalEnum tipoAnimal;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
}
