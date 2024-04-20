package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {
    @InjectMocks
    private InserirPessoaService inserirPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void deveInserirComSucesso() {
        // given
        var pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Nome");
        pessoa.setSobrenome("Sobrenome");
        pessoa.setApelido("Apelido");
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setDataNascimento(LocalDate.now().minusYears(20));

        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        // when
        var pessoaInserida = inserirPessoaService.inserir(pessoa);

        // then
        assertNotNull(pessoaInserida);
    }

    @Test
    public void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        // given
        var pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Nome");
        pessoa.setSobrenome("Sobrenome");
        pessoa.setApelido("Apelido");
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setDataNascimento(LocalDate.now().minusYears(17));

        // when
        assertThrows(SomenteDeMaiorException.class, () -> inserirPessoaService.inserir(pessoa));
    }

    @Test
    public void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        // given
        var pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Nome");
        pessoa.setSobrenome("Sobrenome");
        pessoa.setApelido("Apelido");
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setDataNascimento(LocalDate.now().minusYears(20));
        pessoa.setAnimaisDeEstimacao(new ArrayList<>());
        pessoa.getAnimaisDeEstimacao().add(new Animal());
        pessoa.getAnimaisDeEstimacao().add(new Animal());
        pessoa.getAnimaisDeEstimacao().add(new Animal());

        // when
        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPessoaService.inserir(pessoa));
    }
}