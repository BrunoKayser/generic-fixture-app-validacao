package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.controller.Fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    InserirPessoaService inserirPessoaService;

    @Test
    void inserir() {
        var pessoa = PessoaFixture.criaPessoa();
        pessoa.setDataNascimento(LocalDate.of(2000, 1, 7));

        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        var response = inserirPessoaService.inserir(pessoa);

        assertEquals(pessoa.getId(), response.getId());
    }

    @Test
    void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        var pessoa = PessoaFixture.criaPessoa();
        pessoa.setDataNascimento(LocalDate.now());

        assertThrows(SomenteDeMaiorException.class, () -> inserirPessoaService.inserir(pessoa));
    }

    @Test
    void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        var pessoa = PessoaFixture.criaPessoaCom3Animais();
        pessoa.setDataNascimento(LocalDate.of(2000, 1, 7));

        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPessoaService.inserir(pessoa));
    }
}