package tcc.teste.aplicacao.fixture.generic.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;

@ExtendWith(MockitoExtension.class)
class PetShopMapperTest {

  @InjectMocks
  private PetShopMapper mapper;

  @Mock
  private ModelMapper modelMapper;

  @Test
  void deveMapearPetShopRequestParaPetShopComSucesso() {

    final var petShopRequest = GenericFixture.generate(PetShopRequest.class);

    final var pet = GenericFixture.generate(PetShop.class);

    when(modelMapper.map(petShopRequest, PetShop.class))
        .thenReturn(pet);

    final var result = mapper.toPetShop(petShopRequest);

    assertNotNull(result);
    assertEquals(pet, result);

  }

  @Test
  void deveMapearPetShopInseridaParaPetShopInseridaResponseComSucesso() {

    final var petShopInserida = GenericFixture.generate(PetShopInserida.class);

    final var pet = GenericFixture.generate(PetShopInseridaResponse.class);

    when(modelMapper.map(petShopInserida, PetShopInseridaResponse.class))
        .thenReturn(pet);

    final var result = mapper.toPetShopInseridaResponse(petShopInserida);

    assertNotNull(result);
    assertEquals(pet, result);

  }

  @Test
  void deveMapearPetShopParaPetShopResponseComSucesso() {

    final var petShop = GenericFixture.generate(PetShop.class);

    final var petShopResponse = GenericFixture.generate(PetShopResponse.class);

    when(modelMapper.map(petShop, PetShopResponse.class))
        .thenReturn(petShopResponse);

    final var result = mapper.toPetShopResponse(petShop);

    assertNotNull(result);
    assertEquals(petShopResponse, result);

  }
}