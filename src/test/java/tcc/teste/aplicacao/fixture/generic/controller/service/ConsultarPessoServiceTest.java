package tcc.teste.aplicacao.fixture.generic.controller.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ConsultarPessoServiceTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    ConsultarPessoaService consultarPessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve consultar com sucesso")
    void deveConsultarComSucesso() {

        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(
                Pessoa.builder()
                        .id(1L)
                        .build()
        ));
        Pessoa pessoa = consultarPessoaService.consultar(1L);

        assertNotNull(pessoa);
        assertEquals(1L, pessoa.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção quanto não encontrar pessoa por id")
    void deveLancarExcecaoQuandoNaoEncontrarPessoaPorId() {

        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            consultarPessoaService.consultar(1L);
        });
    }
}
