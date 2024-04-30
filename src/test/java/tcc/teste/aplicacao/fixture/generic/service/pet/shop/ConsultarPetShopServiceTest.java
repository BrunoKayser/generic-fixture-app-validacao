package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
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
    PetShopRepository petShopRepository;

    @InjectMocks
    ConsultarPetShopService consultarPetShopService;

    @Test
    void consultar() {
        var petShop = GenericFixture.generate(PetShop.class);

        when(petShopRepository.findById(any())).thenReturn(Optional.ofNullable(petShop));

        var response = consultarPetShopService.consultar(1L);

        assertEquals(petShop, response);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId() {
        when(petShopRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> consultarPetShopService.consultar(1L));
    }
}