package tcc.teste.aplicacao.fixture.generic.mapper;

import static generic.fixture.GenericFixture.generate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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

@ExtendWith(MockitoExtension.class)
class PetShopMapperTest {
    @InjectMocks
    private PetShopMapper petShopMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void deveMapearPetShopRequestParaPetShopComSucesso() {
        var petShopRequest = generate(PetShopRequest.class);
        var exp = generate(PetShop.class);

        when(modelMapper.map(petShopRequest, PetShop.class))
            .thenReturn(exp);

        var result = petShopMapper.toPetShop(petShopRequest);

        assertEquals(exp, result);
    }

    @Test
    void deveMapearPetShopInseridaParaPetShopInseridaResponse() {
        var petShopInserida = generate(PetShopInserida.class);
        var exp = generate(PetShopInseridaResponse.class);

        when(modelMapper.map(petShopInserida, PetShopInseridaResponse.class))
            .thenReturn(exp);

        var result = petShopMapper.toPetShopInseridaResponse(petShopInserida);

        assertEquals(exp, result);
    }

    @Test
    void deeMapearPetShopParaPetShopResponse() {
        var petShop = generate(PetShop.class);
        var exp = generate(PetShopResponse.class);

        when(modelMapper.map(petShop, PetShopResponse.class))
            .thenReturn(exp);

        var result = petShopMapper.toPetShopResponse(petShop);

        assertEquals(exp, result);
    }
}