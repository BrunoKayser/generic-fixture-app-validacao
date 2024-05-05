package tcc.teste.aplicacao.fixture.generic.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import generic.fixture.GenericFixture;
import org.apache.commons.lang3.RandomUtils;
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

@ExtendWith(MockitoExtension.class)
class PetShopControllerTest {

    @InjectMocks
    PetShopController controller;

    @Mock
    PetShopMapper mapper;

    @Mock
    InserirPetShopService inserirPetShopService;

    @Mock
    ConsultarPetShopService consultarPetShopService;

    @Test
    public void insertTest() {
        var insert = GenericFixture.generate(PetShopInseridaResponse.class);
        when(mapper.toPetShop(any())).thenReturn(GenericFixture.generate(PetShop.class));
        when(mapper.toPetShopInseridaResponse(any())).thenReturn(insert);
        when(inserirPetShopService.inserir(any())).thenReturn(GenericFixture.generate(PetShopInserida.class));

        var response = controller.insert(GenericFixture.generate(PetShopRequest.class));

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody(), insert);
    }

    @Test
    public void getPetShopTest() {
        var consulta = GenericFixture.generate(PetShopResponse.class);
        when(mapper.toPetShopResponse(any())).thenReturn(consulta);
        when(consultarPetShopService.consultar(any())).thenReturn(GenericFixture.generate(PetShop.class));

        var response = controller.getPetShop(RandomUtils.nextLong());

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), consulta);
    }

}