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
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class ConsultarPessoaServiceTest {

    @InjectMocks
    ConsultarPessoaService consultarPessoaService;

    @Mock
    PessoaRepository pessoaRepository;


    @Test
    void shouldGetPersonSuccess(){

        Mockito.when(pessoaRepository.findById(Mockito.any())).thenReturn(Optional.of(mockPerson()));

        Pessoa consultar = consultarPessoaService.consultar(1L);

        Assertions.assertEquals(consultar.getId(), 1L);
    }

    @Test
    void sholdNaoEncontradoException() {

        Mockito.when(pessoaRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NaoEncontradoException.class, () -> consultarPessoaService.consultar(1L));
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