package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.fixture.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

    @InjectMocks
    ConsultarPessoaService service;

    @Mock
    PessoaRepository repository;

    @Test
    public void consultarTest() {
        var pessoa = PessoaFixture.create();
        when(repository.findById(any())).thenReturn(Optional.of(pessoa));

        var response = service.consultar(RandomUtils.nextLong());
        assertNotNull(response);
        assertEquals(response.getId(), pessoa.getId());
    }

    @Test
    public void consultarErrorTest() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> service.consultar(RandomUtils.nextLong()));
    }

}