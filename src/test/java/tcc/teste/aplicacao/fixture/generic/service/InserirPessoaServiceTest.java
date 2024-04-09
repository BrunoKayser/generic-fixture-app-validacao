package tcc.teste.aplicacao.fixture.generic.service;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

    @InjectMocks
    InserirPessoaService inserirPessoaService;

    @Mock
    PessoaRepository pessoaRepository;

    @Test
    void deveInserirComSucesso() {

        var customValue = new HashMap<String, Object>();
        customValue.put("dataNascimento", LocalDate.now().minusYears(19));

        var pessoa = GenericFixture.generate(Pessoa.class, customValue);

        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        var pessoaInseridaRetorno = inserirPessoaService.inserir(pessoa);

        assertEquals(pessoa.getId(), pessoaInseridaRetorno.getId());

    }

    @Test
    void naoDeveInserirQuandoPessoaForMenorDeIdade() {

        var customValue = new HashMap<String, Object>();
        customValue.put("dataNascimento", LocalDate.now().minusYears(5));

        var pessoa = GenericFixture.generate(Pessoa.class, customValue);


        var exception = Assertions.assertThrows(SomenteDeMaiorException.class,
                () -> inserirPessoaService.inserir(pessoa));

        verify(pessoaRepository, never()).save(pessoa);


        assertEquals("Somente maiores de 18 anos podem realizar o cadastro.", exception.getMessage());

    }

}