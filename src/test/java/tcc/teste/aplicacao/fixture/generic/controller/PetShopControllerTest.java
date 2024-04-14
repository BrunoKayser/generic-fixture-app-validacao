package tcc.teste.aplicacao.fixture.generic.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import generic.fixture.GenericFixture;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

    final var petRequest = GenericFixture.generate(PetShopRequest.class);

    final var pet = GenericFixture.generate(PetShop.class);

    final var petInserida = GenericFixture.generate(PetShopInserida.class);

    final var petInseridaResp = GenericFixture.generate(PetShopInseridaResponse.class);

    when(petShopMapper.toPetShop(petRequest))
        .thenReturn(pet);

    when(inserirPetShopService.inserir(pet))
        .thenReturn(petInserida);

    when(petShopMapper.toPetShopInseridaResponse(petInserida))
        .thenReturn(petInseridaResp);

    final var result = controller.insert(petRequest);

    assertNotNull(result);

  }

  @Test
  void deveConsultarComSucesso() {

    final var id = 123L;

    final var pet = GenericFixture.generate(PetShop.class);

    final var petResponse = GenericFixture.generate(PetShopResponse.class);

    when(consultarPetShopService.consultar(id))
        .thenReturn(pet);

    when(petShopMapper.toPetShopResponse(pet))
        .thenReturn(petResponse);

    final var result = controller.getPetShop(id);

    assertNotNull(result);
  }
}