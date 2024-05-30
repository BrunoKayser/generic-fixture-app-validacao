package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.funcionario.Funcionario;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InserirPetShopServiceTest {

    @InjectMocks
    private InserirPetShopService inserirPetShopService;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    public void deveInserirComSucesso() {
        // Arrange
        PetShop petShop = GenericFixture.generate(PetShop.class);
        petShop.setDataInauguracao(LocalDate.now().plusDays(1));
        petShop.setFuncionarios(Arrays.asList(new Funcionario(), new Funcionario()));
        when(petShopRepository.save(petShop)).thenReturn(petShop);

        // Act
        PetShopInserida result = inserirPetShopService.inserir(petShop);

        // Assert
        assertEquals(petShop.getId(), result.getId());
    }

    @Test
    public void deveLancarExceptionQuandoDataDeInauguracaoForNoPassado() {
        // Arrange
        PetShop petShop = GenericFixture.generate(PetShop.class);
        petShop.setDataInauguracao(LocalDate.now().minusDays(1));

        // Act & Assert
        assertThrows(DataInauguracaoException.class, () -> inserirPetShopService.inserir(petShop));
    }

    @Test
    public void deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios() {
        // Arrange
        PetShop petShop = GenericFixture.generate(PetShop.class);
        petShop.setFuncionarios(Collections.singletonList(new Funcionario()));

        // Act & Assert
        assertThrows(QuantidadeDeAnimaisException.class, () -> inserirPetShopService.inserir(petShop));
    }
}
