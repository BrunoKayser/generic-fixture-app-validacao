package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import generic.fixture.GenericFixture;
import java.time.LocalDate;
import java.util.HashMap;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {

    @InjectMocks
    InserirPetShopService service;

    @Mock
    PetShopRepository repository;

    @Test
    public void inserirTest() {
        var petShop = GenericFixture.generate(PetShop.class, 3 );
        when(repository.save(any())).thenReturn(petShop);
        var result = service.inserir(petShop);
        assertNotNull(result);
        assertEquals(petShop.getId(), result.getId());
    }

    @Test
    public void inserirInauguracaoPassadaTest() {
        var hash = new HashMap<String, Object>();
        hash.put("dataInauguracao", LocalDate.now().minusDays(RandomUtils.nextInt()));
        var petShop = GenericFixture.generate(PetShop.class, hash, 3);
        assertThrows(DataInauguracaoException.class, () -> service.inserir(petShop));
    }

    @Test
    public void inserirMenosDoisFuncionariosTest() {
        var petShop = GenericFixture.generate(PetShop.class);
        assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(petShop));
    }

}