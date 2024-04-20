package tcc.teste.aplicacao.fixture.generic.controller;

import static generic.fixture.GenericFixture.generate;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        when(petShopMapper.toPetShop(any()))
            .thenReturn(generate(PetShop.class));

        when(inserirPetShopService.inserir(any()))
            .thenReturn(generate(PetShopInserida.class));

        var exp = generate(PetShopInseridaResponse.class);
        when(petShopMapper.toPetShopInseridaResponse(any()))
            .thenReturn(exp);

        var result = controller.insert(generate(PetShopRequest.class));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(exp, result.getBody());
    }

    @Test
    void deveConsultarComSucesso() {
        when(consultarPetShopService.consultar(any()))
            .thenReturn(generate(PetShop.class));
        var exp = generate(PetShopResponse.class);
        when(petShopMapper.toPetShopResponse(any()))
            .thenReturn(exp);

        var result = controller.getPetShop(nextLong());

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(exp, result.getBody());
    }
}