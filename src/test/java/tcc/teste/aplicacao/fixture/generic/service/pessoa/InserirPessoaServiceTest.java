package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    private InserirPessoaService service;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void deveInserirComSucesso() {
        when(pessoaRepository.save(any())).thenReturn(getPessoa(1995, emptyList()));

        var result = assertDoesNotThrow(() -> service.inserir(getPessoa(1995, emptyList())));

        assertNotNull(result.getId());
    }

    @Test
    void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        assertThrows(SomenteDeMaiorException.class, () -> service.inserir(getPessoa(2020, emptyList())));
    }

    @Test
    void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        var animais = List.of(getAnimal(), getAnimal(), getAnimal());
        assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(getPessoa(1995, animais)));
    }

    private Pessoa getPessoa(int anoNascimento, List<Animal> animais) {
        return Pessoa.builder()
                .id(1L)
                .animaisDeEstimacao(animais)
                .nome("Lucas")
                .apelido("Loky")
                .genero(GeneroEnum.NAO_BINARIO)
                .sobrenome("Rech")
                .dataNascimento(LocalDate.of(anoNascimento, 11, 30))
                .build();
    }

    private Animal getAnimal() {
        return Animal.builder()
                .id(1L)
                .nome("Céu")
                .dataNascimento(LocalDate.of(2010, 11, 11))
                .raca("Akita")
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .pessoa(getPessoa(1995, emptyList()))
                .build();
    }

}