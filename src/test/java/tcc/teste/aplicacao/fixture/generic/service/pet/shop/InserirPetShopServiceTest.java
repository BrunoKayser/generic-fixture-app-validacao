package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {

    @InjectMocks
    private InserirPetShopService tested;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    public void deveInserirComSucesso() {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put("dataInauguracao", LocalDate.now());
        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 3);

        when(petShopRepository.save(petShop)).thenReturn(petShop);

        var result = tested.inserir(petShop);

        assertEquals(petShop.getId(), result.getId());
    }

    @Test
    public void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put("dataInauguracao", LocalDate.now().minusDays(2));
        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 3);

       assertThrows(DataInauguracaoException.class, () -> tested.inserir(petShop));
    }

    @Test
    public void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put("dataInauguracao", LocalDate.now());
        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 1);

        assertThrows(QuantidadeDeAnimaisException.class, () -> tested.inserir(petShop));
    }
}