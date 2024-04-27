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
    PetShopMapper petShopMapper;

    @Mock
    ModelMapper modalMapper;

    @Test
    void deveMapearPetShopRequestParaPetShopComSucesso(){
        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);

        when(modalMapper.map(petShopRequest,PetShop.class)).thenReturn(petShop);

        PetShop actual = petShopMapper.toPetShop(petShopRequest);

        assertEquals(actual,petShop);
    }

    @Test
    void deveMapearPetShopInseridaParaPetShopInseridaResponseComSucesso(){
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);
        PetShopInseridaResponse expected = GenericFixture.generate(PetShopInseridaResponse.class);

        when(modalMapper.map(petShopInserida,PetShopInseridaResponse.class)).thenReturn(expected);

        PetShopInseridaResponse actual = petShopMapper.toPetShopInseridaResponse(petShopInserida);

        assertEquals(actual,expected);

    }

    @Test
    void deveMapearPetShopParaPetShopResponseComSucesso(){
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopResponse expected = GenericFixture.generate(PetShopResponse.class);

        when(modalMapper.map(petShop,PetShopResponse.class)).thenReturn(expected);

        PetShopResponse actual = petShopMapper.toPetShopResponse(petShop);

        assertEquals(expected,actual);

    }


}