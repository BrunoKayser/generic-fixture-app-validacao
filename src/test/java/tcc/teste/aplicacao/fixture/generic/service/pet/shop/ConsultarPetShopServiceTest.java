package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import generic.fixture.GenericFixture;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {

  @InjectMocks
  private ConsultarPetShopService service;

  @Mock
  private PetShopRepository petShopRepository;

  @Test
  void deveConsultarComSucesso() {

    final var id = 123L;

    final var pet = GenericFixture.generate(PetShop.class);

    when(petShopRepository.findById(id))
        .thenReturn(Optional.of(pet));

    final var result = service.consultar(id);

    assertNotNull(result);
    assertEquals(pet, result);

  }

  @Test
  void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId() {

    final var id = 123L;

    when(petShopRepository.findById(id))
        .thenReturn(Optional.empty());

    final var ex = assertThrows(NaoEncontradoException.class, () -> service.consultar(id));

    assertNotNull(ex);
    assertNotNull(ex.getMessage());
    assertEquals(String.format("PetShop com %d não encontrada", id), ex.getMessage());

  }
}