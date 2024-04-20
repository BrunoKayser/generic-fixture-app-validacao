package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.time.LocalDate;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {

    @InjectMocks
    InserirPetShopService inserirPetShopService;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    public void deveInserirComSucesso() {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put("dataInauguracao", LocalDate.now());
        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 4);

        when(petShopRepository.save(any(PetShop.class))).thenReturn(petShop);

        PetShopInserida petShopInserida = inserirPetShopService.inserir(petShop);

        Assertions.assertNotNull(petShopInserida);
    }

    @Test
    public void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put("dataInauguracao", LocalDate.now().minusYears(1));
        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 4);

        Assertions.assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));
    }

    @Test
    public void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {
        HashMap<String, Object> customFields = new HashMap<>();
        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 1);

        Assertions.assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }
}