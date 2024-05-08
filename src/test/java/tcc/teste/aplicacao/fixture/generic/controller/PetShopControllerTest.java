package tcc.teste.aplicacao.fixture.generic.controller;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
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
    private PetShopController controller;

    @Mock
    private PetShopMapper petShopMapper;

    @Mock
    private InserirPetShopService inserirPetShopService;

    @Mock
    private ConsultarPetShopService consultarPetShopService;

    @Test
    void deveInserirComSucesso() {
        when(inserirPetShopService.inserir(any())).thenReturn(GenericFixture.generate(PetShopInserida.class));
        when(petShopMapper.toPetShopInseridaResponse(any())).thenReturn(GenericFixture.generate(PetShopInseridaResponse.class));

        var result = assertDoesNotThrow(() -> controller.insert(GenericFixture.generate(PetShopRequest.class)));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    void deveConsultarComSucesso() {
        when(petShopMapper.toPetShopResponse(any())).thenReturn(GenericFixture.generate(PetShopResponse.class));

        var result = assertDoesNotThrow(() -> controller.getPetShop(1L));

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

}