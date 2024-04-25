package tcc.teste.aplicacao.fixture.generic.controller;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
class PetShopControllerTest {

    @Mock
    InserirPetShopService inserirPetShopService;

    @Mock
    ConsultarPetShopService consultarPetShopService;

    @Mock
    PetShopMapper petShopMapper;


    @InjectMocks
    PetShopController petShopController;


    @Test
    void shouldInsertPetSuccess() {

        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);
        PetShopInseridaResponse petShopInseridaResponse = GenericFixture.generate(PetShopInseridaResponse.class);

        Mockito.when(petShopMapper.toPetShop(Mockito.any())).thenReturn(petShop);
        Mockito.when(petShopMapper.toPetShopInseridaResponse(Mockito.any())).thenReturn(petShopInseridaResponse);
        Mockito.when(inserirPetShopService.inserir(Mockito.any())).thenReturn(petShopInserida);

        ResponseEntity<PetShopInseridaResponse> insertPetResponse = petShopController.insert(petShopRequest);

        Assertions.assertNotNull(insertPetResponse);
    }

    @Test
    void shouldConsultPetSuccess() {
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopResponse petShopResponse = GenericFixture.generate(PetShopResponse.class);

        Mockito.when(consultarPetShopService.consultar(Mockito.any())).thenReturn(petShop);
        Mockito.when(petShopMapper.toPetShopResponse(Mockito.any())).thenReturn(petShopResponse);

        ResponseEntity<PetShopResponse>  petShopGetResponse = petShopController.getPetShop(1L);

        Assertions.assertNotNull(petShopGetResponse);
    }

}