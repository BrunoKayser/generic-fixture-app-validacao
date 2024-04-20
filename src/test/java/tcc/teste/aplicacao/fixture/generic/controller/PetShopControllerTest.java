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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopControllerTest {

    @InjectMocks
    PetShopController petShopController;

    @Mock
    private PetShopMapper petShopMapper;

    @Mock
    private InserirPetShopService inserirPetShopService;

    @Mock
    private ConsultarPetShopService consultarPetShopService;

    @Test
    public void deveInserirComSucesso() {
        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopInseridaResponse petShopInseridaResponse = GenericFixture.generate(PetShopInseridaResponse.class);
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);

        when(petShopMapper.toPetShop(any(PetShopRequest.class))).thenReturn(petShop);
        when(petShopMapper.toPetShopInseridaResponse(any(PetShopInserida.class))).thenReturn(petShopInseridaResponse);
        when(inserirPetShopService.inserir(any(PetShop.class))).thenReturn(petShopInserida);

        ResponseEntity<PetShopInseridaResponse> responseEntity = petShopController.insert(petShopRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(petShopInseridaResponse, responseEntity.getBody());
    }

    @Test
    public void deveConsultarComSucesso() {
        PetShopResponse petShopResponse = GenericFixture.generate(PetShopResponse.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);

        when(consultarPetShopService.consultar(any(Long.class))).thenReturn(petShop);
        when(petShopMapper.toPetShopResponse(any(PetShop.class))).thenReturn(petShopResponse);

        ResponseEntity<PetShopResponse> responseEntity = petShopController.getPetShop(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(petShopResponse, responseEntity.getBody());
    }
}