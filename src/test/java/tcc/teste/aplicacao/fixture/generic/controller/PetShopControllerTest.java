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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetShopControllerTest {

    @InjectMocks
    private PetShopController petShopController;

    @Mock
    private PetShopMapper petShopMapper;

    @Mock
    private InserirPetShopService inserirPetShopService;

    @Mock
    private ConsultarPetShopService consultarPetShopService;

    @Test
    public void deveInserirComSucesso() {
        // Arrange
        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class);
        PetShop petShop = new PetShop();
        PetShopInserida petShopInserida = new PetShopInserida();
        when(petShopMapper.toPetShop(any())).thenReturn(petShop);
        when(inserirPetShopService.inserir(any())).thenReturn(petShopInserida);

        // Act
        ResponseEntity<PetShopInseridaResponse> response = petShopController.insert(petShopRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void deveConsultarComSucesso() {
        // Arrange
        Long id = 1L;
        PetShop petShop = new PetShop();
        when(consultarPetShopService.consultar(id)).thenReturn(petShop);

        // Act
        ResponseEntity<PetShopResponse> response = petShopController.getPetShop(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}