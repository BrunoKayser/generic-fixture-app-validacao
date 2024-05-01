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
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {


    @InjectMocks
    private ConsultarPetShopService service;

    @Mock
    private PetShopRepository repository;

    @Test
    void consultar() {

        Long id = 123L;

        PetShop petShop = GenericFixture.generate(PetShop.class);
        Optional<PetShop> petShopOptional = Optional.of(petShop);

        Mockito.doReturn(petShopOptional).when(repository).findById(id);

        PetShop result = service.consultar(id);

        assertEquals(petShopOptional.get(), result);
    }

    @Test
    void consultarComErro() {
        Long id = 123L;

        Mockito.doReturn(Optional.empty()).when(repository).findById(id);

        Assertions.assertThrows(NaoEncontradoException.class, () -> service.consultar(id));
    }
}