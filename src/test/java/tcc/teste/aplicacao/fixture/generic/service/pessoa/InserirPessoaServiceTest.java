package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static tcc.teste.aplicacao.fixture.generic.controller.utils.MockUtils.mockAnimal;
import static tcc.teste.aplicacao.fixture.generic.controller.utils.MockUtils.mockPessoa;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    private InserirPessoaService inserirPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void deveInserirComSucesso() {
        Pessoa pessoa = mockPessoa();
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        assertDoesNotThrow(() -> inserirPessoaService.inserir(pessoa));
    }

    @Test
    void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        Pessoa pessoa = mockPessoa();
        pessoa.setDataNascimento(pessoa.getDataNascimento().plusYears(30));
        assertThrows(SomenteDeMaiorException.class, () -> inserirPessoaService.inserir(pessoa));
    }

    @Test
    void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        Pessoa pessoa = mockPessoa();
        pessoa.setAnimaisDeEstimacao(List.of(mockAnimal(), mockAnimal(), mockAnimal()));
        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPessoaService.inserir(pessoa));
    }
}