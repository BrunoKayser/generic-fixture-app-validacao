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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetShopMapperTest {

    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    PetShopMapper petShopMapper;

    @Test
    void testToPetShop() {
        var petShop = GenericFixture.generate(PetShop.class);
        when(modelMapper.map(any(), any())).thenReturn(petShop);
        PetShop result = petShopMapper.toPetShop(GenericFixture.generate(PetShopRequest.class));
        assertNotNull(result);
        assertEquals(petShop, result);
    }

    @Test
    void testToPetShopInseridaResponse() {
        var request = GenericFixture.generate(PetShopInseridaResponse.class);
        when(modelMapper.map(any(), any())).thenReturn(request);
        PetShopInseridaResponse result = petShopMapper.toPetShopInseridaResponse(GenericFixture.generate(PetShopInserida.class));
        assertNotNull(result);
        assertEquals(request, result);
    }

    @Test
    void testToPetShopResponse() {
        var request = GenericFixture.generate(PetShopResponse.class);
        when(modelMapper.map(any(), any())).thenReturn(request);
        PetShopResponse result = petShopMapper.toPetShopResponse(GenericFixture.generate(PetShop.class));
        assertNotNull(result);
        assertEquals(request, result);
    }
}
