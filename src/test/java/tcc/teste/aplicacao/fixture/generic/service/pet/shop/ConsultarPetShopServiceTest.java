package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {

    @InjectMocks
    ConsultarPetShopService consultarPetShopService;

    @Mock
    PetShopRepository petShopRepository;

    @Test
    void deveConsultarComSucesso(){
        PetShop petShop = GenericFixture.generate(PetShop.class);
        Mockito.when(petShopRepository.findById(petShop.getId())).thenReturn(Optional.of(petShop));

        PetShop actual = consultarPetShopService.consultar(petShop.getId());

        assertEquals(petShop,actual);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId(){

        Mockito.when(petShopRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () ->  consultarPetShopService.consultar(1l));

    }
}
