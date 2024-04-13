package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import generic.fixture.GenericFixture;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoPredioEnum;
import tcc.teste.aplicacao.fixture.generic.domain.funcionario.Funcionario;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {

  @InjectMocks
  InserirPetShopService service;

  @Mock
  private PetShopRepository petShopRepository;

  @Test
  void deveInserirComSucesso() {

    HashMap<String, Object> customFields = new HashMap<>();
    customFields.put("dataInauguracao", LocalDate.now().plusYears(10));

    PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 2);

    when(petShopRepository.save(petShop))
        .thenReturn(petShop);

    final var result = service.inserir(petShop);

    assertNotNull(result);
    assertEquals(petShop.getId(), result.getId());

  }

  @Test
  void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {

    HashMap<String, Object> customFields = new HashMap<>();
    customFields.put("dataInauguracao", LocalDate.now().minusYears(10));
    PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 2);

    final var ex = assertThrows(DataInauguracaoException.class, () -> service.inserir(petShop));

    assertNotNull(ex);
    assertEquals("A data de inauguração não pode ser no passado.", ex.getMessage());

    verifyNoInteractions(petShopRepository);

  }

  @Test
  void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {


    HashMap<String, Object> customFields = new HashMap<>();
    customFields.put("dataInauguracao", LocalDate.now().plusYears(10));
    PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 1);

    final var ex = assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(petShop));

    assertNotNull(ex);
    assertEquals("O Máximo de animais cadastrados por pessoa deve ser 2.", ex.getMessage());

    verifyNoInteractions(petShopRepository);

  }
}