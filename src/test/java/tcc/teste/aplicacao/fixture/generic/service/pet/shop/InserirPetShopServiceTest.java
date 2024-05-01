package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.DisplayName;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirPetShopServiceTest {


    @InjectMocks
    InserirPetShopService inserirPetShopService;

    @Mock
    PetShopRepository petShopRepository;

    PetShop petShop;


    @Test
    @DisplayName("Deve inserir um PetShop")
    void testInserir() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("dataInauguracao", LocalDate.now());

        petShop = GenericFixture.generate(PetShop.class, params, 3);
        when(petShopRepository.save(any())).thenReturn(petShop);

        var response = inserirPetShopService.inserir(petShop);

        assertEquals(petShop.getId(), response.getId());
    }

    @Test
    @DisplayName("Não deve inserir um PetShop - Inauguracao antes da data atual")
    void testInserirFail() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("dataInauguracao", LocalDate.of(1992, 02, 12));

        petShop = GenericFixture.generate(PetShop.class, params, 3);

        assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));

    }

    @Test
    @DisplayName("Não deve inserir um PetShop - Menos de 2 funcionarios")
    void testInserirFailFuncionarios() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("dataInauguracao", LocalDate.now());

        petShop = GenericFixture.generate(PetShop.class, params, 0);

        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }
}