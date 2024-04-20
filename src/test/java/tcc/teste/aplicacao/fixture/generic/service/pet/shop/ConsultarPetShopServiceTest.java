package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {

    @InjectMocks
    ConsultarPetShopService consultarPetShopService;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    public void deveConsultarComSucesso() {
        PetShop petShop = GenericFixture.generate(PetShop.class);

        when(petShopRepository.findById(any(Long.class))).thenReturn(Optional.of(petShop));

        PetShop petShopReturn = consultarPetShopService.consultar(1L);

        assertNotNull(petShopReturn);
    }

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId() {
        when(petShopRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> consultarPetShopService.consultar(1L));
    }
}