package tcc.teste.aplicacao.fixture.generic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;

@ExtendWith(MockitoExtension.class)
public class ConsultarPessoaServiceTest {
    
    @InjectMocks
    ConsultarPessoaService consultarPessoaService;

    @Mock
    PessoaRepository pessoaRepository;

    @Test
    public void deveConsultarComSucesso() {
        var pessoa = new Pessoa();

        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setSobrenome("Sobrenome");
        pessoa.setAnimaisDeEstimacao(List.of(new Animal(1l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null)));

        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pessoa));

        var resposta = consultarPessoaService.consultar(1l);

        assertEquals(resposta, pessoa);
    }
    
    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new NaoEncontradoException("Abacate"));

        assertThrows(NaoEncontradoException.class, () -> consultarPessoaService.consultar(1l));
    }
        
}
