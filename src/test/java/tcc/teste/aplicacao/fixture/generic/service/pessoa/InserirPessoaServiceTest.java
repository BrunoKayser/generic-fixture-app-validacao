package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.util.Mocks.*;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    private InserirPessoaService inserirPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    @DisplayName("Deve inserir com sucesso")
    void deveInserirComSucesso() {
        Pessoa pessoa = gerarPessoa();
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        PessoaInserida result = inserirPessoaService.inserir(pessoa);

        assertEquals(pessoa.getId(), result.getId());
    }

    @Test
    @DisplayName("Deve lancar exception quando pessoa for menor de idade")
    void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        Pessoa pessoa = gerarPessoa();
        pessoa.setDataNascimento(LocalDate.now());

        SomenteDeMaiorException e = assertThrows(
                SomenteDeMaiorException.class,
                () -> inserirPessoaService.inserir(pessoa)
        );

        assertEquals("Somente maiores de 18 anos podem realizar o cadastro.", e.getMessage());
    }

    @Test
    @DisplayName("Deve lancar exception quando pessoa ter mais que dois animais")
    void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        Pessoa pessoa = gerarPessoa();
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        for (int i = 0; i < 3; i++) {
            pessoa.getAnimaisDeEstimacao().add(gerarAnimal());
        }

        QuantidadeDeAnimaisException e = assertThrows(
                QuantidadeDeAnimaisException.class,
                () -> inserirPessoaService.inserir(pessoa)
        );

        assertEquals("O Máximo de animais cadastrados por pessoa deve ser 2.", e.getMessage());
    }
}
