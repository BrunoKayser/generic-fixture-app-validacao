package tcc.teste.aplicacao.fixture.generic.mapper;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PetShopMapperTest {

    @InjectMocks
    private PetShopMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void toPetShop() {
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopRequest request = GenericFixture.generate(PetShopRequest.class);

        doReturn(petShop).when(modelMapper).map(request, PetShop.class);

        PetShop result = mapper.toPetShop(request);

        Assertions.assertEquals(petShop, result);
    }

    @Test
    void toPetShopInseridaResponse() {
        PetShopInseridaResponse response = GenericFixture.generate(PetShopInseridaResponse.class);
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);

        doReturn(response).when(modelMapper).map(petShopInserida, PetShopInseridaResponse.class);

        PetShopInseridaResponse result = mapper.toPetShopInseridaResponse(petShopInserida);

        assertEquals(response, result);
    }

    @Test
    void toPetShopResponse() {
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopResponse response = GenericFixture.generate(PetShopResponse.class);

        doReturn(response).when(modelMapper).map(petShop, PetShopResponse.class);

        PetShopResponse result = mapper.toPetShopResponse(petShop);

        assertEquals(response, result);
    }
}