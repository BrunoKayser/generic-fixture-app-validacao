package tcc.teste.aplicacao.fixture.generic.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import generic.fixture.GenericFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;

@ExtendWith(MockitoExtension.class)
public class PetShopMapperTest {

    @InjectMocks
    private PetShopMapper petShopMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearPetShopRequestParaPetShopComSucesso() {
        var petShopRequest = GenericFixture.generate(PetShopRequest.class);
        var petShop = GenericFixture.generate(PetShop.class);

        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(petShop);

        var resposta = petShopMapper.toPetShop(petShopRequest);

        assertEquals(petShop, resposta);
    }

    @Test
    public void deveMapearPetShopInseridaParaPetShopInseridaResponseComSucesso() {
        var petShopResponse = GenericFixture.generate(PetShopInseridaResponse.class);
        var petShopInserida = GenericFixture.generate(PetShopInserida.class);

        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(petShopResponse);

        var resposta = petShopMapper.toPetShopInseridaResponse(petShopInserida);

        assertEquals(petShopResponse, resposta);
    }

    @Test
    public void deveMapearPetShopParaPetShopResponseComSucesso() {
        var petShop = GenericFixture.generate(PetShop.class);
        var petShopResponse = GenericFixture.generate(PetShopResponse.class);

        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(petShopResponse);

        var resposta = petShopMapper.toPetShopResponse(petShop);

        assertEquals(petShopResponse, resposta);
    }
    
}
