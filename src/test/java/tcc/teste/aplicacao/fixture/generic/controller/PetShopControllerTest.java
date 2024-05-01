package tcc.teste.aplicacao.fixture.generic.controller;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PetShopMapper;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.ConsultarPetShopService;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.InserirPetShopService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopControllerTest {

    @InjectMocks
    PetShopController petShopController;
    @Mock
    PetShopMapper petShopMapper;
    @Mock
    InserirPetShopService inserirPetShopService;
    @Mock
    ConsultarPetShopService consultarPetShopService;

    PetShopRequest petShopRequest;
    PetShop petShop;
    PetShopInserida petShopInserida;
    PetShopInseridaResponse petShopInseridaResponse;
    PetShopResponse petShopResponse;


    @Test
    @DisplayName("Deve inserir um novo PetShop")
    void testInsert() {
        petShopRequest = GenericFixture.generate(PetShopRequest.class);
        petShop = GenericFixture.generate(PetShop.class);
        petShopInserida = GenericFixture.generate(PetShopInserida.class);
        petShopInseridaResponse = GenericFixture.generate(PetShopInseridaResponse.class);


        when(petShopMapper.toPetShop(any())).thenReturn(petShop);
        when(inserirPetShopService.inserir(any())).thenReturn(petShopInserida);
        when(petShopMapper.toPetShopInseridaResponse(any())).thenReturn(petShopInseridaResponse);

        var response = petShopController.insert(petShopRequest);

        assertEquals(response.getBody(), petShopInseridaResponse);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    @DisplayName("Deve buscar um petshop pelo id")
    void testGetPetShop() {
        petShopResponse = GenericFixture.generate(PetShopResponse.class);
        when(consultarPetShopService.consultar(any())).thenReturn(petShop);
        when(petShopMapper.toPetShopResponse(any())).thenReturn(petShopResponse);


        var response = petShopController.getPetShop(1L);

        assertEquals(response.getBody(), petShopResponse);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}