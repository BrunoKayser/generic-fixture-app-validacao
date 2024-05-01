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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {

    @InjectMocks
    private InserirPetShopService service;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    void inserir() {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put("dataInauguracao", LocalDate.now().plusYears(1));

        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 3);

        PetShop petShopInserida = petShop;
        petShopInserida.setId(123L);

        PetShopInserida expected = PetShopInserida.builder().id(petShopInserida.getId()).build();

        Mockito.doReturn(petShopInserida).when(petShopRepository).save(petShop);

        PetShopInserida result = service.inserir(petShop);

        assertEquals(expected.getId(), result.getId());
    }

    @Test
    void inserirComErroDataInauguracaoNoPassado() {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put("dataInauguracao", LocalDate.now().minusYears(1));

        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 3);

        Assertions.assertThrows(DataInauguracaoException.class, ()-> service.inserir(petShop));
    }

    @Test
    void inserirComErroNumeroDeFuncionarios() {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put("dataInauguracao", LocalDate.now().plusYears(1));

        PetShop petShop = GenericFixture.generate(PetShop.class, customFields, 1);

        Assertions.assertThrows(QuantidadeDeAnimaisException.class, ()-> service.inserir(petShop));
    }
}