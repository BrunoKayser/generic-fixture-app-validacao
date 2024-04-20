package tcc.teste.aplicacao.fixture.generic.domain.pessoa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static java.util.Objects.nonNull;
import static tcc.teste.aplicacao.fixture.generic.constants.Constantes.DEZOITO_ANOS;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Long id;
    private String nome;
    private String apelido;
    private GeneroEnum genero;
    private String sobrenome;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Animal> animaisDeEstimacao;

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public boolean ehDeMaior() {
        return getIdade() >= DEZOITO_ANOS;
    }

    public void setPessoaNosAnimais() {
        if (nonNull(animaisDeEstimacao)) {
            animaisDeEstimacao
                    .forEach(animal -> animal.setPessoa(this));
        }
    }

}