package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import static generic.fixture.GenericFixture.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

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
    private InserirPetShopService service;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    void deveInserirComSucesso() {
        var petShop = generate(PetShop.class, 2);
        var inserida = generate(PetShop.class, 2);

        when(petShopRepository.save(petShop))
            .thenReturn(inserida);

        var result = service.inserir(petShop);

        assertEquals(inserida.getId(), result.getId());
    }

    @Test
    void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {
        var petShop = generate(PetShop.class);

        assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(petShop));
    }

    @Test
    void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {
        var petShop = generate(PetShop.class, 2);
        petShop.setDataInauguracao(LocalDate.now().minusDays(2));

        assertThrows(DataInauguracaoException.class, () -> service.inserir(petShop));
    }
}