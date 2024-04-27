package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.fixture.AnimaisFixture;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    InserirPessoaService inserirPessoaService;

    @Mock
    PessoaRepository pessoaRepository;


    @Test
    void deveInserirComSucesso(){
        Pessoa pessoa = PessoaFixture.pessoa();
        PessoaInserida pessoaInserida = PessoaInserida.builder().id(1l).build();
        Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        PessoaInserida actual = inserirPessoaService.inserir(pessoa);

        assertEquals(actual.getId(), pessoaInserida.getId());
    }

    @Test
    void deveLancarExceptionQuandoPessoaForMenorDeIdade(){
        Pessoa pessoa = PessoaFixture.pessoa();
        pessoa.setDataNascimento(LocalDate.now().minusYears(2));
        assertThrows(SomenteDeMaiorException.class,() -> inserirPessoaService.inserir(pessoa));
    }

    @Test
    void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais(){
        Pessoa pessoa = PessoaFixture.pessoa();
        pessoa.setAnimaisDeEstimacao(List.of(AnimaisFixture.criarAnimal(),AnimaisFixture.criarAnimal(),AnimaisFixture.criarAnimal()));

        assertThrows(QuantidadeDeAnimaisException.class,() -> inserirPessoaService.inserir(pessoa));
    }

}