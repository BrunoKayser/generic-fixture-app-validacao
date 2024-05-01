package tcc.teste.aplicacao.fixture.generic.controller;

import generic.fixture.GenericFixture;
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
import static org.mockito.Mockito.doReturn;

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
    void insert() {
        PetShopRequest request = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);
        PetShopInseridaResponse response = GenericFixture.generate(PetShopInseridaResponse.class);

        doReturn(petShop).when(petShopMapper).toPetShop(request);

        doReturn(petShopInserida).when(inserirPetShopService).inserir(petShop);

        doReturn(response).when(petShopMapper).toPetShopInseridaResponse(petShopInserida);

        ResponseEntity<PetShopInseridaResponse> result = controller.insert(request);

        assertEquals(response, result.getBody());
    }

    @Test
    void getPetShop() {
        Long id = 123L;

        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopResponse response = GenericFixture.generate(PetShopResponse.class);

        doReturn(petShop).when(consultarPetShopService).consultar(id);

        doReturn(response).when(petShopMapper).toPetShopResponse(petShop);

        ResponseEntity<PetShopResponse> result = controller.getPetShop(id);

        assertEquals(response, result.getBody());
    }
}