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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {


    @InjectMocks
    private InserirPetShopService inserirPetShopService;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    void deveInserirComSucesso() {
        HashMap propriedades = new HashMap();
        propriedades.put("dataInauguracao", LocalDate.now().plusYears(1));
        PetShop petShop = GenericFixture.generate(PetShop.class, propriedades, 2);

        when(petShopRepository.save(any(PetShop.class))).thenReturn(petShop);

        PetShopInserida response = inserirPetShopService.inserir(petShop);

        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {
        HashMap propriedades = new HashMap();
        propriedades.put("dataInauguracao", LocalDate.now().minusYears(1));
        PetShop petShop = GenericFixture.generate(PetShop.class, propriedades, 2);

        assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));
    }

    @Test
    void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {
        HashMap propriedades = new HashMap();
        propriedades.put("dataInauguracao", LocalDate.now().plusYears(1));
        PetShop petShop = GenericFixture.generate(PetShop.class, propriedades, 1);

        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }
}