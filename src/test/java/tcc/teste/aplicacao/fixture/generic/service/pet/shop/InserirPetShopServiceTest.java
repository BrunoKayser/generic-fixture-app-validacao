package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {

    @Mock
    PetShopRepository petShopRepository;

    @InjectMocks
    InserirPetShopService inserirPetShopService;

    @Test
    void inserir() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("dataInauguracao", LocalDate.now());
        var petShop = GenericFixture.generate(PetShop.class, map, 3);

        when(petShopRepository.save(any())).thenReturn(petShop);
        var response = inserirPetShopService.inserir(petShop);

        assertEquals(petShop.getId(), response.getId());
    }

    @Test
    void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("dataInauguracao", LocalDate.of(2000, 1,1));
        var petShop = GenericFixture.generate(PetShop.class, map);

        assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));
    }

    @Test
    void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("dataInauguracao", LocalDate.now());
        var petShop = GenericFixture.generate(PetShop.class, map, 1);

        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }
}