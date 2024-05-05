package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.fixture.AnimalFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    InserirPessoaService service;

    @Mock
    PessoaRepository repository;

    @Test
    public void inserirTest() {

        var pessoa = PessoaFixture.create();
        pessoa.setDataNascimento(LocalDate.now().minusYears(19));
        when(repository.save(any())).thenReturn(pessoa);

        var response = service.inserir(pessoa);
        assertNotNull(response);
        assertEquals(response.getId(), pessoa.getId());
    }

    @Test
    public void inserirPessoaMenorTest() {
        var pessoa = PessoaFixture.create();
        assertThrows(SomenteDeMaiorException.class, () -> service.inserir(pessoa));
    }

    @Test
    public void inserirMaisDoisAnimaisTest() {

        var pessoa = PessoaFixture.create();
        var animais = Arrays.asList(AnimalFixture.create(), AnimalFixture.create(), AnimalFixture.create());

        pessoa.setDataNascimento(LocalDate.now().minusYears(19));
        pessoa.setAnimaisDeEstimacao(animais);

        assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(pessoa));
    }

}