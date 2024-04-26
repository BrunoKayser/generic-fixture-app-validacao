package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.AnimalFixture;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    private InserirPessoaService tested;

    @Mock
    private PessoaRepository repository;

    @Test
    public void deveInserirComSucesso() {
        Pessoa pessoa = PessoaFixture.criarPessoa();
        pessoa.setDataNascimento(LocalDate.of(2000, 1, 1));

        when(repository.save(pessoa)).thenReturn(pessoa);

        PessoaInserida result = tested.inserir(pessoa);
        assertEquals(result.getId(), pessoa.getId());
    }

    @Test
    public void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        Pessoa pessoa = PessoaFixture.criarPessoa();

        assertThrows(SomenteDeMaiorException.class, () -> tested.inserir(pessoa));
    }

    @Test
    public void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        Pessoa pessoa = PessoaFixture.criarPessoa();
        Animal animal = AnimalFixture.criarAnimal();
        List<Animal> animais = List.of(animal, animal, animal);
        pessoa.setDataNascimento(LocalDate.of(2000, 1, 1));
        pessoa.setAnimaisDeEstimacao(animais);

        assertThrows(QuantidadeDeAnimaisException.class, () -> tested.inserir(pessoa));
    }

}