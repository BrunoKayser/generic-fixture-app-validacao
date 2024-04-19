package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.controller.fixture.AnimalFixture;
import tcc.teste.aplicacao.fixture.generic.controller.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.controller.fixture.PessoaInseridaFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    InserirPessoaService inserirPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void deveInserirComSucesso() {
        Pessoa pessoa = PessoaFixture.create();
        pessoa.setDataNascimento(LocalDate.now().minusYears(20));

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaInserida pessoaInserida = inserirPessoaService.inserir(pessoa);

        assertEquals(1L, pessoaInserida.getId());
    }

    @Test
    public void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        Pessoa pessoa = PessoaFixture.create();

        assertThrows(SomenteDeMaiorException.class, () -> inserirPessoaService.inserir(pessoa));
    }

    @Test
    public void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        Pessoa pessoa = PessoaFixture.create();
        pessoa.setDataNascimento(LocalDate.now().minusYears(20));
        pessoa.getAnimaisDeEstimacao().add(AnimalFixture.create());
        pessoa.getAnimaisDeEstimacao().add(AnimalFixture.create());

        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPessoaService.inserir(pessoa));
    }
}