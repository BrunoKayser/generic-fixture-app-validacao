package tcc.teste.aplicacao.fixture.generic.controller;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopControllerTest {

    @InjectMocks
    private PetShopController petShopController;

    @Mock
    private InserirPetShopService inserirPetShopService;

    @Mock
    private ConsultarPetShopService consultarPetShopService;

    @Mock
    private PetShopMapper patShopMapper;

    @Test
    void deveInserirComSucesso() {
        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class);
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopInseridaResponse petShopInseridaResponse = GenericFixture.generate(PetShopInseridaResponse.class);

        when(patShopMapper.toPetShop(petShopRequest)).thenReturn(petShop);
        when(inserirPetShopService.inserir(any())).thenReturn(petShopInserida);
        when(patShopMapper.toPetShopInseridaResponse(petShopInserida)).thenReturn(petShopInseridaResponse);

        ResponseEntity<PetShopInseridaResponse> response = petShopController.insert(petShopRequest);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody(), petShopInseridaResponse);
    }

    @Test
    void deveConsultarComSucesso() {
        Long id = 1L;
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopResponse petShopResponse = GenericFixture.generate(PetShopResponse.class);

        when(consultarPetShopService.consultar(id)).thenReturn(petShop);
        when(patShopMapper.toPetShopResponse(petShop)).thenReturn(petShopResponse);

        ResponseEntity<PetShopResponse> response = petShopController.getPetShop(id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), petShopResponse);
    }
}