package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    private InserirPessoaService service;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void inserirComSucesso() {
        Pessoa pessoa = getPessoaFixture();
        Pessoa pessoaInserida = getPessoaFixture();
        pessoaInserida.setId(123L);

        PessoaInserida expected = PessoaInserida.builder().id(pessoaInserida.getId()).build();

        doReturn(pessoaInserida).when(pessoaRepository).save(pessoa);

        PessoaInserida result = service.inserir(pessoa);

        Assertions.assertEquals(expected.getId(), result.getId());
    }

    @Test
    void inserirComErroNumeroAnimais() {
        Pessoa pessoa = getPessoaFixture();
        pessoa.setAnimaisDeEstimacao(getAnimalListComTresAnimais());

        Assertions.assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(pessoa));
    }

    @Test
    void inserirComErroMenorDeIdade() {
        Pessoa pessoa = getPessoaFixture();
        pessoa.setDataNascimento(LocalDate.of(2023,5,1));

        Assertions.assertThrows(SomenteDeMaiorException.class, () -> service.inserir(pessoa));
    }


    private Pessoa getPessoaFixture() {
        return Pessoa.builder()
                .nome("teste")
                .apelido("abc")
                .genero(GeneroEnum.MASCULINO)
                .dataNascimento(LocalDate.of(1990,5,1))
                .animaisDeEstimacao(getAnimalList())
                .build();
    }


    private List<Animal> getAnimalList() {
        Animal animal1 = Animal.builder()
                .id(1L)
                .tipoAnimal(TipoAnimalEnum.GATO)
                .build();

        Animal animal2 = Animal.builder()
                .id(2L)
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .build();

        return List.of(animal1, animal2);
    }

    private List<Animal> getAnimalListComTresAnimais() {
        Animal animal1 = Animal.builder()
                .id(1L)
                .tipoAnimal(TipoAnimalEnum.GATO)
                .build();

        Animal animal2 = Animal.builder()
                .id(2L)
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .build();

        Animal animal3 = Animal.builder()
                .id(3L)
                .tipoAnimal(TipoAnimalEnum.CACHORRO)
                .build();

        return List.of(animal1, animal2, animal3);
    }


}