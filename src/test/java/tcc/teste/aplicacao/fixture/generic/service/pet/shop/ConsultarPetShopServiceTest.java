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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarPetShopServiceTest {

    @InjectMocks
    private ConsultarPetShopService consultarPetShopService;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    public void deveConsultarComSucesso() {
        // Arrange
        Long id = 1L;
        PetShop petShop = GenericFixture.generate(PetShop.class);
        when(petShopRepository.findById(id)).thenReturn(Optional.of(petShop));

        // Act
        PetShop result = consultarPetShopService.consultar(id);

        // Assert
        assertEquals(petShop, result);
    }

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrado() {
        // Arrange
        Long id = 1L;
        when(petShopRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NaoEncontradoException.class, () -> consultarPetShopService.consultar(id));
    }
}