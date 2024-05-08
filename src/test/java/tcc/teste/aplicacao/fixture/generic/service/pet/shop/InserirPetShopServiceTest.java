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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {

    @InjectMocks
    private InserirPetShopService service;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    void deveInserirComSucesso() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dataInauguracao", LocalDate.now());
        when(petShopRepository.save(any())).thenReturn(GenericFixture.generate(PetShop.class));

        var result = assertDoesNotThrow(() -> service.inserir(GenericFixture.generate(PetShop.class, parametros, 3)));

        assertNotNull(result.getId());
    }

    @Test
    void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dataInauguracao", LocalDate.now().minusDays(30));

        assertThrows(DataInauguracaoException.class, () -> service.inserir(GenericFixture.generate(PetShop.class, parametros, 3)));
    }

    @Test
    void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dataInauguracao", LocalDate.now());

        assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(GenericFixture.generate(PetShop.class, parametros, 1)));
    }
}