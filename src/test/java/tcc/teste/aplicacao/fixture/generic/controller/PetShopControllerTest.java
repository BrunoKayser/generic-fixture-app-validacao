package tcc.teste.aplicacao.fixture.generic.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import generic.fixture.GenericFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PetShopMapper;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.ConsultarPetShopService;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.InserirPetShopService;

@ExtendWith(MockitoExtension.class)
public class PetShopControllerTest {

    @InjectMocks
    private PetShopController  petShopController;

    @Mock
    private InserirPetShopService inserirPetShopService;

    @Mock
    private ConsultarPetShopService consultarPetShopService;

    @Mock
    private PetShopMapper petShopMapper;

    @Test
    public void deveInserirComSucesso() {
        
        var petShopRequest = GenericFixture.generate(PetShopRequest.class);
        var esperado = GenericFixture.generate(PetShopInseridaResponse.class);
        Mockito.when(inserirPetShopService.inserir(Mockito.any(PetShop.class))).thenReturn(GenericFixture.generate(PetShopInserida.class));
        Mockito.when(petShopMapper.toPetShop(Mockito.any(PetShopRequest.class))).thenReturn(GenericFixture.generate(PetShop.class));
        Mockito.when(petShopMapper.toPetShopInseridaResponse(Mockito.any(PetShopInserida.class))).thenReturn(esperado);

        var resposta = petShopController.insert(petShopRequest);

        assertEquals(esperado, resposta.getBody());
    }

    @Test
    public void deveConsultarComSucesso() {
        var esperado = GenericFixture.generate(PetShopResponse.class);
        Mockito.when(consultarPetShopService.consultar(Mockito.anyLong())).thenReturn(GenericFixture.generate(PetShop.class));
        Mockito.when(petShopMapper.toPetShopResponse(Mockito.any(PetShop.class))).thenReturn(esperado);

        var resposta = petShopController.getPetShop(1l);

        assertEquals(esperado, resposta.getBody());
    }
    
}
