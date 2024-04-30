package tcc.teste.aplicacao.fixture.generic.controller;

import generic.fixture.GenericFixture;
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
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.ConsultarPetShopService;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.InserirPetShopService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopControllerTest {

    @Mock
    PetShopMapper petShopMapper;

    @Mock
    InserirPetShopService inserirPetShopService;

    @Mock
    ConsultarPetShopService consultarPetShopService;

    @InjectMocks
    PetShopController petShopController;

    @Test
    void insert() {
        var petShopRequest = GenericFixture.generate(PetShopRequest.class);
        var petShop = GenericFixture.generate(PetShop.class);
        var petShopInserida =  GenericFixture.generate(PetShopInserida.class);

        when(petShopMapper.toPetShop(any())).thenReturn(petShop);
        when(inserirPetShopService.inserir(any())).thenReturn(petShopInserida);

        var response = petShopController.insert(petShopRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getPetShop() {
        var petShop = GenericFixture.generate(PetShop.class);
        var petShopResponse = GenericFixture.generate(PetShopResponse.class);

        when(consultarPetShopService.consultar(1L)).thenReturn(petShop);
        when(petShopMapper.toPetShopResponse(any())).thenReturn(petShopResponse);

        var response = petShopController.getPetShop(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}