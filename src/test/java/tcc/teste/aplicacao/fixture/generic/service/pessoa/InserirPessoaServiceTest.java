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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    InserirPessoaService inserirPessoaService;

    @Mock
    PessoaRepository repository;

    @Test
    void sholdSavePersonSuccess() {

        Mockito.when(repository.save(Mockito.any())).thenReturn(mockPerson());

        PessoaInserida pessoaInserida = inserirPessoaService.inserir(mockPerson());

        Assertions.assertEquals(pessoaInserida.getId(), 1L);
        Assertions.assertNotNull(pessoaInserida);

    }

    @Test
    void sholdSomenteDeMaiorExceptionException() {
        Pessoa person = mockPerson();
        person.setDataNascimento(LocalDate.now());

        Assertions.assertThrows(SomenteDeMaiorException.class, () -> inserirPessoaService.inserir(person));
    }

    @Test
    void sholdQuantidadeDeAnimaisException() {
        Pessoa person = mockPerson();
        Animal animalDois = new Animal(2L, "Pedrinho", LocalDate.now(), "Vira Latis", TipoAnimalEnum.CACHORRO, new Pessoa());
        Animal animalTres = new Animal(2L, "Jorginho", LocalDate.now(), "Vira Latis", TipoAnimalEnum.CACHORRO, new Pessoa());
        person.getAnimaisDeEstimacao().add(animalDois);
        person.getAnimaisDeEstimacao().add(animalTres);

        Assertions.assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPessoaService.inserir(person));
    }

    Pessoa mockPerson() {

        // Dados Animal
        List<Animal> animalRequestList = new ArrayList<>();

        TipoAnimalEnum cachorro = TipoAnimalEnum.CACHORRO;
        Animal animalUm = new Animal(1L, "Zezinho", LocalDate.now(), "Vira Latis", TipoAnimalEnum.CACHORRO, new Pessoa());

        animalRequestList.add(animalUm);

        // Dados Pessoa
        GeneroEnum masculino = GeneroEnum.MASCULINO;
        return new Pessoa(1L, "Brunao", "Lindo", masculino, "Kayser", LocalDate.now().minusYears(20), animalRequestList);
    }

}