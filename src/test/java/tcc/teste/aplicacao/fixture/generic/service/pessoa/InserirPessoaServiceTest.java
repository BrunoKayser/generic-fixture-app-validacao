package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    InserirPessoaService service;

    Pessoa pessoa;
    PessoaInserida pessoaInserida;

    @BeforeEach
    void init() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setSobrenome("Sobrenome");
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setAnimaisDeEstimacao(List.of(new Animal(1L, "Rex", LocalDate.now(), "Caramelo", TipoAnimalEnum.CACHORRO, null)));

        pessoaInserida = new PessoaInserida();
        pessoaInserida.setId(1L);

    }

    @DisplayName("Deve inserir uma pessoa e retornar a PessoaInserida")
    @Test
    void testInserir() {
        when(pessoaRepository.save(any())).thenReturn(pessoa);

        pessoa.setDataNascimento(LocalDate.of(2005, 02, 23));

        assertDoesNotThrow(() -> service.inserir(pessoa));

        verify(pessoaRepository, times(1)).save(any());
    }

    @DisplayName("Não deve inserir uma pessoa menor de idade")
    @Test
    void testInserirFailMenorDeIdade() {
        assertThrows(SomenteDeMaiorException.class, () -> service.inserir(pessoa));
    }

    @DisplayName("Não deve inserir uma pessoa com mais de um pet")
    @Test
    void testInserirManyPets() {
        var list = new ArrayList<Animal>();
        list.add(new Animal(1L, "Rex", LocalDate.now(), "Caramelo", TipoAnimalEnum.CACHORRO, null));
        list.add(new Animal(1L, "Rex", LocalDate.now(), "Caramelo", TipoAnimalEnum.CACHORRO, null));
        list.add(new Animal(1L, "Rex", LocalDate.now(), "Caramelo", TipoAnimalEnum.CACHORRO, null));
        pessoa.setAnimaisDeEstimacao(list);
        pessoa.setDataNascimento(LocalDate.of(2005, 02, 23));

        assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(pessoa));
    }
}