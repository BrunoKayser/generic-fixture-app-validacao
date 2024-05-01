package tcc.teste.aplicacao.fixture.generic.mapper;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopMapperTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PetShopMapper petShopMapper;

    PetShopInseridaResponse petShopInseridaResponse;
    PetShopInserida petShopInserida;
    PetShop petShop;
    PetShopResponse petShopResponse;
    PetShopRequest petShopRequest;

    @BeforeEach
    void init() {
        petShopInseridaResponse = GenericFixture.generate(PetShopInseridaResponse.class);
        petShopInserida = GenericFixture.generate(PetShopInserida.class);
        petShop = GenericFixture.generate(PetShop.class);
        petShopResponse = GenericFixture.generate(PetShopResponse.class);
        petShopRequest = GenericFixture.generate(PetShopRequest.class);
    }

    @Test
    void testToPetShopInseridaResponse() {
        when(modelMapper.map(any(), any())).thenReturn(petShopInseridaResponse);
        var response = petShopMapper.toPetShopInseridaResponse(petShopInserida);

        assertNotNull(response);
        assertEquals(response, petShopInseridaResponse);
    }

    @Test
    void testToPetShopResponse() {
        when(modelMapper.map(any(), any())).thenReturn(petShopResponse);
        var response = petShopMapper.toPetShopResponse(petShop);

        assertNotNull(response);
        assertEquals(response, petShopResponse);
    }

    @Test
    void testToPetShop() {
        when(modelMapper.map(any(), any())).thenReturn(petShop);
        var response = petShopMapper.toPetShop(petShopRequest);

        assertNotNull(response);
        assertEquals(response, petShop);
    }
}