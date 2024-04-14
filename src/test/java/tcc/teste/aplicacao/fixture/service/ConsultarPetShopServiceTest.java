package tcc.teste.aplicacao.fixture.service;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.ConsultarPetShopService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ConsultarPetShopServiceTest {

    @Mock
    PetShopRepository petShopRepository;

    @InjectMocks
    ConsultarPetShopService consultarPetShopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve consultar com sucesso")
    void deveConsultarComSucesso() {
        PetShop expected = GenericFixture.generate(PetShop.class, 2);

        when(petShopRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        PetShop result = consultarPetShopService.consultar(1L);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNome(), result.getNome());
        assertEquals(expected.getDataInauguracao(), result.getDataInauguracao());
        assertEquals(expected.getFuncionarios().size(), result.getFuncionarios().size());
        assertEquals(expected.getEndereco(), result.getEndereco());
        assertEquals(expected.getTipoPredio(), result.getTipoPredio());

        for (int i = 0; i < expected.getFuncionarios().size(); i++) {
            assertEquals(expected.getFuncionarios().get(i).getNome(), result.getFuncionarios().get(i).getNome());
            assertEquals(expected.getFuncionarios().get(i).getSobrenome(), result.getFuncionarios().get(i).getSobrenome());
            assertEquals(expected.getFuncionarios().get(i).getGenero(), result.getFuncionarios().get(i).getGenero());
            assertEquals(expected.getFuncionarios().get(i).getDataNascimento(), result.getFuncionarios().get(i).getDataNascimento());
        }
    }

    @Test
    @DisplayName("Deve lançar exceção quando não encontrar PetShop pelo id")
    void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId() {
        when(petShopRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> consultarPetShopService.consultar(1L));
    }
}
