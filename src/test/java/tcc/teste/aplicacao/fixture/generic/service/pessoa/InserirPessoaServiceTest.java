package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.AnimalFixture;
import tcc.teste.aplicacao.fixture.generic.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InserirPessoaServiceTest {

    @InjectMocks
    private InserirPessoaService inserirPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void deveInserirComSucesso() {
        // Arrange
        Pessoa pessoa = PessoaFixture.getPessoa();
        PessoaInserida pessoaInserida = new PessoaInserida(1L);
        when(pessoaRepository.save(any())).thenReturn(pessoa);

        // Act
        PessoaInserida result = inserirPessoaService.inserir(pessoa);

    }

    @Test
    public void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        // Arrange
        Pessoa pessoa = PessoaFixture.getPessoa();
        pessoa.setDataNascimento(LocalDate.of(2010, 1, 1));

        // Act & Assert
        assertThrows(SomenteDeMaiorException.class, () -> inserirPessoaService.inserir(pessoa));
    }

    @Test
    public void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        // Arrange
        Pessoa pessoa = PessoaFixture.getPessoa();
        Animal animalExtra = AnimalFixture.getAnimal();
        List list = Arrays.asList(animalExtra, animalExtra, animalExtra);
        animalExtra.setNome("Extra");
        pessoa.setAnimaisDeEstimacao(list);


        // Act & Assert
        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPessoaService.inserir(pessoa));
    }
}