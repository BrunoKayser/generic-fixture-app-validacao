package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {

    @Mock
    PetShopRepository repository;

    @InjectMocks
    ConsultarPetShopService consultarPetShopService;

    PetShop petShop;

    @BeforeEach
    void init() {
        petShop = GenericFixture.generate(PetShop.class);
    }

    @Test
    @DisplayName("Deve consultar um Petshop pelo ID")
    void testConsultar() {
        when(repository.findById(any())).thenReturn(Optional.of(petShop));

        var response = assertDoesNotThrow(() -> consultarPetShopService.consultar(1L));

        assertEquals(response, petShop);
    }

    @Test
    @DisplayName("Deve consultar um Petshop pelo ID")
    void testConsultarFail() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> consultarPetShopService.consultar(1L));
    }

}