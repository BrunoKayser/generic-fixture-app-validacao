package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Assertions;
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
    void sholdInsertPetShopSuccess() {

        HashMap<String, Object> customObject = new HashMap<>();
        customObject.put("dataInauguracao", LocalDate.now().plusYears(2));

        PetShop petShop = GenericFixture.generate(PetShop.class, customObject, 3);

        Mockito.when(petShopRepository.save(Mockito.any())).thenReturn(petShop);

        PetShopInserida inserirPetShopResponse = inserirPetShopService.inserir(petShop);

        Assertions.assertNotNull(inserirPetShopResponse);
    }

    @Test
    void shouldDataInauguracaoException() {

        HashMap<String, Object> customObject = new HashMap<>();
        customObject.put("dataInauguracao", LocalDate.now().minusYears(1));
        PetShop petShop = GenericFixture.generate(PetShop.class, customObject);

        Assertions.assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));

    }

    @Test
    void sholdQuantidadeDeAnimaisException() {
        PetShop petShop = GenericFixture.generate(PetShop.class,1);

        Assertions.assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }



}