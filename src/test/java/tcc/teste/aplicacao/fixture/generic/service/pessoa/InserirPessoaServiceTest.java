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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @Mock
    private PessoaRepository repository;
    @InjectMocks
    private InserirPessoaService inserirPessoaService;

    @Test
    void inserir() {
        Pessoa pessoa = Pessoa.builder()
                .id(1L)
                .nome("João")
                .apelido("Joãozinho")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1990, 5, 15))
                .animaisDeEstimacao(Arrays.asList(
                        Animal.builder()
                                .id(1L)
                                .nome("Rex")
                                .dataNascimento(LocalDate.of(2015, 3, 10))
                                .raca("Labrador")
                                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                                .build(),
                        Animal.builder()
                                .id(2L)
                                .nome("Mia")
                                .dataNascimento(LocalDate.of(2018, 7, 20))
                                .raca("Persa")
                                .tipoAnimal(TipoAnimalEnum.GATO)
                                .build()
                ))
                .build();

        var pessoaInserida = PessoaInserida.builder().id(1L).build();

        when(repository.save(pessoa)).thenReturn(pessoa);

        var pessoaInseridaACtual = inserirPessoaService.inserir(pessoa);

        assertEquals(pessoaInseridaACtual.getId(), pessoaInserida.getId());
    }

    @Test
    void naoDeveTerMaisQueDoisAnimais() {

        Pessoa pessoa = Pessoa.builder()
                .id(1L)
                .nome("João")
                .apelido("Joãozinho")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1990, 5, 15))
                .animaisDeEstimacao(Arrays.asList(
                        Animal.builder()
                                .id(1L)
                                .nome("Rex")
                                .dataNascimento(LocalDate.of(2015, 3, 10))
                                .raca("Labrador")
                                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                                .build(),
                        Animal.builder()
                                .id(2L)
                                .nome("Mia")
                                .dataNascimento(LocalDate.of(2018, 7, 20))
                                .raca("Persa")
                                .tipoAnimal(TipoAnimalEnum.GATO)
                                .build(),
                        Animal.builder()
                                .id(2L)
                                .nome("Mia")
                                .dataNascimento(LocalDate.of(2018, 7, 20))
                                .raca("Persa")
                                .tipoAnimal(TipoAnimalEnum.GATO)
                                .build()
                ))
                .build();


        assertThrows(QuantidadeDeAnimaisException.class, () -> {
            inserirPessoaService.inserir(pessoa);
        });
    }


    @Test
    void pessoaDeveSerDeMaior() {

        Pessoa pessoa = Pessoa.builder()
                .id(1L)
                .nome("João")
                .apelido("Joãozinho")
                .genero(GeneroEnum.MASCULINO)
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(2010, 5, 15))
                .animaisDeEstimacao(Arrays.asList(
                        Animal.builder()
                                .id(1L)
                                .nome("Rex")
                                .dataNascimento(LocalDate.of(2015, 3, 10))
                                .raca("Labrador")
                                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                                .build(),
                        Animal.builder()
                                .id(2L)
                                .nome("Mia")
                                .dataNascimento(LocalDate.of(2018, 7, 20))
                                .raca("Persa")
                                .tipoAnimal(TipoAnimalEnum.GATO)
                                .build()
                ))
                .build();


        assertThrows(SomenteDeMaiorException.class, () -> {
            inserirPessoaService.inserir(pessoa);
        });
    }


}