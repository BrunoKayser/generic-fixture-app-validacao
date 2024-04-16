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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {

    @InjectMocks
    private ConsultarPetShopService consultarPetShopService;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    void deveConsultarComSucesso() {
        // Arrange
        Long id = 1L;
        PetShop petShopMock = GenericFixture.generate(PetShop.class);

        when(petShopRepository.findById(id)).thenReturn(java.util.Optional.of(petShopMock));
        // Act
        PetShop petShop = consultarPetShopService.consultar(id);

        // Assert
        assertEquals(petShopMock, petShop);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId() {
        // Arrange
        Long id = 1L;

        when(petShopRepository.findById(id)).thenReturn(Optional.empty());
        // Act
        // Assert
        assertThrows(NaoEncontradoException.class, () -> consultarPetShopService.consultar(id));
    }


}