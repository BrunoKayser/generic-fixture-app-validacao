package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @Mock
    PessoaRepository repository;

    @InjectMocks
    ConsultarPessoaService service;

    Pessoa pessoa;

    @BeforeEach
    void init() {
        pessoa = new Pessoa();
        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setSobrenome("Sobrenome");
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setAnimaisDeEstimacao(List.of(new Animal(1L, "Rex", LocalDate.now(), "Caramelo", TipoAnimalEnum.CACHORRO, null)));

    }

    @Test
    @DisplayName("Deve consultar uma pessoa com id - SUCESS")
    void testConsultarSucess() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(pessoa));

        var response = assertDoesNotThrow(() -> service.consultar(1L));

        assertEquals(response, pessoa);
    }

    @Test
    @DisplayName("Deve consultar uma pessoa com id - Fail")
    void testConsultarFail() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.consultar(1L));
    }

}