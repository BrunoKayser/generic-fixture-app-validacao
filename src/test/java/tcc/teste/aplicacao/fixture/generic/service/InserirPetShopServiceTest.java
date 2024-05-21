package tcc.teste.aplicacao.fixture.generic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import generic.fixture.GenericFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.InserirPetShopService;

@ExtendWith(MockitoExtension.class)
public class InserirPetShopServiceTest {
    
    @InjectMocks
    private InserirPetShopService inserirPetShopService;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    public void deveInserirComSucesso() {
        var fields = new HashMap<String, Object>();
        fields.put("dataInauguracao", LocalDate.now());

        var petShop = GenericFixture.generate(PetShop.class, fields, 2);
        Mockito.when(petShopRepository.save(Mockito.any(PetShop.class))).thenReturn(petShop);
        var resposta = inserirPetShopService.inserir(petShop);
        assertEquals(petShop.getId(), resposta.getId());
    }

    @Test
    public void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {
        var fields = new HashMap<String, Object>();
        fields.put("dataInauguracao", LocalDate.now().minusYears(2));

        var petShop = GenericFixture.generate(PetShop.class, fields, 2);
        
        assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));
    }

    @Test
    public void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {
        var fields = new HashMap<String, Object>();
        fields.put("dataInauguracao", LocalDate.now());

        var petShop = GenericFixture.generate(PetShop.class, fields, 1);
        
        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }
}
