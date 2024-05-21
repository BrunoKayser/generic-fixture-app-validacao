package tcc.teste.aplicacao.fixture.generic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

@ExtendWith(MockitoExtension.class)
public class InserirPessoaServiceTest {

    @InjectMocks
    private InserirPessoaService inserirPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void deveInserirComSucesso() {
        var pessoa = new Pessoa();

        pessoa.setId(1l);
        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setDataNascimento(LocalDate.now().minusYears(18));
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setSobrenome("Sobrenome");
        pessoa.setAnimaisDeEstimacao(
                List.of(new Animal(1l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null)));

        Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(pessoa);

        var resposta = inserirPessoaService.inserir(pessoa);

        assertEquals(resposta.getId(), pessoa.getId());
    }

    @Test
    public void deveLancarExceptionQuandoPessoaForMenorDeIdade() {
        var pessoa = new Pessoa();

        pessoa.setId(1l);
        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setDataNascimento(LocalDate.now().minusYears(17));
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setSobrenome("Sobrenome");
        pessoa.setAnimaisDeEstimacao(
                List.of(new Animal(1l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null)));

        assertThrows(SomenteDeMaiorException.class, () -> inserirPessoaService.inserir(pessoa));
    }

    @Test
    public void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {
        var pessoa = new Pessoa();

        pessoa.setId(1l);
        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setDataNascimento(LocalDate.now().minusYears(19));
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setSobrenome("Sobrenome");
        pessoa.setAnimaisDeEstimacao(
                List.of(new Animal(1l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null),
                        new Animal(2l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null),
                        new Animal(3l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null)));

        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPessoaService.inserir(pessoa));
    }
}
