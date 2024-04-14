package tcc.teste.aplicacao.fixture.generic.controller.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class InserirPessoaServiceTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    InserirPessoaService inserirPessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve inserir com sucesso")
    void deveInserirComSucesso() {
        Pessoa pessoa = PessoaFixture.pessoaComAnimal(1);
        pessoa.setId(1L);

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        PessoaInserida result = inserirPessoaService.inserir(pessoa);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção quando pessoa for menor de idade")
    void deveLancarExcecaoQuandoPessoaForMenorDeIdade() {
        Pessoa pessoa = PessoaFixture.pessoaComAnimal(1);
        pessoa.setDataNascimento(LocalDate.now().minusYears(17));

        assertThrows(SomenteDeMaiorException.class, () -> {
            inserirPessoaService.inserir(pessoa);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção quando pessoa tiver mais de 2 animais")
    void deveLancarExcecaoQuandoPessoaTiverMaisDeDoisAnimais() {
        Pessoa pessoa = PessoaFixture.pessoaComAnimal(3);

        assertThrows(QuantidadeDeAnimaisException.class, () -> {
            inserirPessoaService.inserir(pessoa);
        });
    }
}
