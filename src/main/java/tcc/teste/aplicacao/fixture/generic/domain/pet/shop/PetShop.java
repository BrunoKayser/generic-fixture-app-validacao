package tcc.teste.aplicacao.fixture.generic.domain.pet.shop;

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
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoPredioEnum;
import tcc.teste.aplicacao.fixture.generic.domain.funcionario.Funcionario;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class PetShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_shop_id")
    private Long id;
    private String nome;
    private String endereco;
    private TipoPredioEnum tipoPredio;
    private LocalDate dataInauguracao;

    @OneToMany(mappedBy = "petShop", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;


    public void setPetShopNosFuncionarios() {
        if(nonNull(funcionarios)) {
            funcionarios
                    .forEach(funcionario -> funcionario.setPetShop(this));
        }

    }

}