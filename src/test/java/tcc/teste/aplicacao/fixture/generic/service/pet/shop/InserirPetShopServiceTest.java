package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {

    @InjectMocks
    InserirPetShopService inserirPetShopService;

    @Mock
    PetShopRepository petShopRepository;

    @Test
    void deveInserirComSucesso(){
        HashMap hashMap = new HashMap();
        hashMap.put("dataInauguracao", LocalDate.now());
        PetShop petShop = GenericFixture.generate(PetShop.class,hashMap,2);
        PetShopInserida expected = GenericFixture.generate(PetShopInserida.class);
        expected.setId(petShop.getId());

        Mockito.when(petShopRepository.save(petShop)).thenReturn(petShop);

        PetShopInserida actual = inserirPetShopService.inserir(petShop);

        assertEquals(expected.getId(),actual.getId());

    }

    @Test
    void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado(){
        HashMap hashMap = new HashMap();
        hashMap.put("dataInauguracao", LocalDate.now().minusYears(1));
        PetShop petShop = GenericFixture.generate(PetShop.class,hashMap,2);

        assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));

    }

    @Test
    void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios(){
        HashMap hashMap = new HashMap();
        hashMap.put("dataInauguracao", LocalDate.now());
        PetShop petShop = GenericFixture.generate(PetShop.class,hashMap,1);
        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }


}