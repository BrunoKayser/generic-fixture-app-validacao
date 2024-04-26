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
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.ConsultarPetShopService;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.InserirPetShopService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopControllerTest {

    @InjectMocks
    private PetShopController tested;

    @Mock
    private PetShopMapper petShopMapper;

    @Mock
    private InserirPetShopService inserirPetShopService;

    @Mock
    private ConsultarPetShopService consultarPetShopService;

    @Test
    public void deveInserirComSucesso() {
        PetShopRequest request = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class);
        PetShopInseridaResponse petShopInseridaResponse = GenericFixture.generate(PetShopInseridaResponse.class);

        when(petShopMapper.toPetShop(request)).thenReturn(petShop);
        when(inserirPetShopService.inserir(petShop)).thenReturn(petShopInserida);
        when(petShopMapper.toPetShopInseridaResponse(petShopInserida)).thenReturn(petShopInseridaResponse);

        var result = tested.insert(request);

        assertAll("Inserir petshop assertions",
                () -> assertEquals(result.getBody(), petShopInseridaResponse),
                () -> assertEquals(HttpStatus.CREATED, result.getStatusCode())
        );
    }

    @Test
    public void deveConsultarComSucesso() {
        Long id = 1L;
        PetShop petShop = GenericFixture.generate(PetShop.class);
        PetShopResponse petShopResponse = GenericFixture.generate(PetShopResponse.class);

        when(consultarPetShopService.consultar(id)).thenReturn(petShop);
        when(petShopMapper.toPetShopResponse(petShop)).thenReturn(petShopResponse);

        var result = tested.getPetShop(id);

        assertEquals(result.getBody(), petShopResponse);
    }
}