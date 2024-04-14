package tcc.teste.aplicacao.fixture.service;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.InserirPetShopService;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class InserirPetShopServiceTest {

    @Mock
    PetShopRepository petShopRepository;

    @InjectMocks
    InserirPetShopService inserirPetShopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve inserir com sucesso")
    void deveInserirComSucesso() {
        PetShop expected = GenericFixture.generate(PetShop.class, 2);
        expected.setDataInauguracao(LocalDate.now());

        when(petShopRepository.save(any(PetShop.class))).thenReturn(expected);

        PetShopInserida result = inserirPetShopService.inserir(expected);

        assertEquals(expected.getId(), result.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção quando data de inauguração for no passado")
    void deveLancarExcecaoQuandoDataDeInauguracaoForNoPassado() {
        PetShop petShop = GenericFixture.generate(PetShop.class, 2);
        petShop.setDataInauguracao(LocalDate.now().minusDays(1));

        when(petShopRepository.save(any(PetShop.class))).thenReturn(petShop);

        assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));
    }

    @Test
    @DisplayName("Deve lançar exceção quando PetShop tiver menos que dois funcionários")
    void deveLancarExcecaoQuandoPetShopTerMenosQueDoisFuncionarios() {
        PetShop petShop = GenericFixture.generate(PetShop.class);

        when(petShopRepository.save(any(PetShop.class))).thenReturn(petShop);

        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }

}
