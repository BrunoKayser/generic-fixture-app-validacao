package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomAnimal;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomPessoa;
import static tcc.teste.aplicacao.fixture.generic.testutils.Fixtures.randomPessoaBuilder;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {
    @InjectMocks
    private InserirPessoaService service;

    @Mock
    private PessoaRepository repository;


    @Test
    void deveInserirComSucesso() {
        var pessoa = randomPessoaBuilderValido()
            .build();

        when(repository.save(pessoa))
            .thenReturn(randomPessoaBuilder().id(1L).build());

        var result = service.inserir(pessoa);

        assertEquals(1L, result.getId());
    }

    @Test
    void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        var pessoa = randomPessoaBuilderValido()
            .dataNascimento(LocalDate.now().minusYears(15))
            .build();

        assertThrows(SomenteDeMaiorException.class,
            () -> service.inserir(pessoa));
    }

    @Test
    void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        var pessoa = randomPessoaBuilderValido()
            .animaisDeEstimacao(List.of(randomAnimal(), randomAnimal(), randomAnimal()))
            .build();

        assertThrows(QuantidadeDeAnimaisException.class,
            () -> service.inserir(pessoa));
    }

    private Pessoa.PessoaBuilder randomPessoaBuilderValido() {
        return randomPessoaBuilder()
            .dataNascimento(LocalDate.now().minusYears(18))
            .animaisDeEstimacao(List.of(randomAnimal(), randomAnimal()));
    }
}