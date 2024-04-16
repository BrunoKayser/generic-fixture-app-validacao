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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopMapperTest {

    @InjectMocks
    private PetShopMapper petShopMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void deveMapearPetShopRequestParaPetShopComSucesso() {
        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);

        when(modelMapper.map(petShopRequest, PetShop.class)).thenReturn(petShop);

        PetShop result = petShopMapper.toPetShop(petShopRequest);

        assertEquals(petShop, result);
    }

    @Test
    void deveMapearPetShopInseridaParaPetShopInseridaResponseComSucesso() {
        PetShopInseridaResponse petShopInseridaResponse = GenericFixture.generate(PetShopInseridaResponse.class);
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);
        when(modelMapper.map(petShopInserida, PetShopInseridaResponse.class)).thenReturn(petShopInseridaResponse);

        PetShopInseridaResponse result = petShopMapper.toPetShopInseridaResponse(petShopInserida);

        assertEquals(petShopInseridaResponse, result);
    }

    @Test
    void deveMapearPetShopParaPetShopResponseComSucesso() {
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopResponse petShopResponse = GenericFixture.generate(PetShopResponse.class);
        when(modelMapper.map(petShop, PetShopResponse.class)).thenReturn(petShopResponse);

        PetShopResponse result = petShopMapper.toPetShopResponse(petShop);

        assertEquals(petShopResponse, result);
    }
}