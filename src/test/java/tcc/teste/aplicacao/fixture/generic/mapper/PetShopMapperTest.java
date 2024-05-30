package tcc.teste.aplicacao.fixture.generic.mapper;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetShopMapperTest {

    @InjectMocks
    private PetShopMapper petShopMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearPetShopRequestParaPetShopComSucesso() {
        // Arrange
        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = new PetShop();
        when(modelMapper.map(petShopRequest, PetShop.class)).thenReturn(petShop);

        // Act
        PetShop result = petShopMapper.toPetShop(petShopRequest);

        // Assert
        assertEquals(petShop, result);
    }

    @Test
    public void deveMapearPetShopInseridaParaPetShopInseridaResponseComSucesso() {
        // Arrange
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);
        PetShopInseridaResponse petShopInseridaResponse = new PetShopInseridaResponse();
        when(modelMapper.map(petShopInserida, PetShopInseridaResponse.class)).thenReturn(petShopInseridaResponse);

        // Act
        PetShopInseridaResponse result = petShopMapper.toPetShopInseridaResponse(petShopInserida);

        // Assert
        assertEquals(petShopInseridaResponse, result);
    }

    @Test
    public void deveMapearPetShopParaPetShopResponseComSucesso() {
        // Arrange
        PetShop petShop = new PetShop();
        PetShopResponse petShopResponse = GenericFixture.generate(PetShopResponse.class);
        when(modelMapper.map(petShop, PetShopResponse.class)).thenReturn(petShopResponse);

        // Act
        PetShopResponse result = petShopMapper.toPetShopResponse(petShop);

        // Assert
        assertEquals(petShopResponse, result);
    }
}