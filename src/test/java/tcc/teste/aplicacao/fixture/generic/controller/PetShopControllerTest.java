package tcc.teste.aplicacao.fixture.generic.controller;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PetShopMapper;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.ConsultarPetShopService;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.InserirPetShopService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(
        MockitoExtension.class
)
class PetShopControllerTest {

    @InjectMocks
    PetShopController petShopController;

    @Mock
    InserirPetShopService inserirPetShopService;

    @Mock
    ConsultarPetShopService consultarPetShopService;

    @Mock
    PetShopMapper petShopMapper;

    @Test
    void deveInserirComSucesso(){
        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);
        PetShopInseridaResponse expected = GenericFixture.generate(PetShopInseridaResponse.class);

        Mockito.when(inserirPetShopService.inserir(petShop)).thenReturn(petShopInserida);
        Mockito.when(petShopMapper.toPetShop(petShopRequest)).thenReturn(petShop);
        Mockito.when(petShopMapper.toPetShopInseridaResponse(petShopInserida)).thenReturn(expected);

        ResponseEntity<PetShopInseridaResponse> actual = petShopController.insert(petShopRequest);

        assertEquals(expected, actual.getBody());
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());

    }

    @Test
    void deveConsultarComSucesso(){
        Long id = 1l;
        PetShopResponse expected = GenericFixture.generate(PetShopResponse.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);

        Mockito.when(consultarPetShopService.consultar(id)).thenReturn(petShop);
        Mockito.when(petShopMapper.toPetShopResponse(petShop)).thenReturn(expected);

        ResponseEntity<PetShopResponse> actual = petShopController.getPetShop(id);

        assertEquals(expected, actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }
}