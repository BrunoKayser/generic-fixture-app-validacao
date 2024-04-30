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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopMapperTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PetShopMapper petShopMapper;

    @Test
    void toPetShop() {
        var petShopRequest = GenericFixture.generate(PetShopRequest.class);
        var petShop = GenericFixture.generate(PetShop.class);

        when(modelMapper.map(any(), any())).thenReturn(petShop);
        var response = petShopMapper.toPetShop(petShopRequest);

        assertEquals(petShop, response);
    }

    @Test
    void toPetShopInseridaResponse() {
        var petShopInserida =  GenericFixture.generate(PetShopInserida.class);
        var petShopInseridaResponse = GenericFixture.generate(PetShopInseridaResponse.class);

        when(modelMapper.map(any(), any())).thenReturn(petShopInseridaResponse);
        var response = petShopMapper.toPetShopInseridaResponse(petShopInserida);

        assertEquals(petShopInseridaResponse, response);
    }

    @Test
    void toPetShopResponse() {
        var petShop = GenericFixture.generate(PetShop.class);
        var petShopResponse =  GenericFixture.generate(PetShopResponse.class);

        when(modelMapper.map(any(), any())).thenReturn(petShopResponse);
        var response = petShopMapper.toPetShopResponse(petShop);

        assertEquals(petShopResponse, response);
    }
}