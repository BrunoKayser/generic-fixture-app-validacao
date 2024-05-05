package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import generic.fixture.GenericFixture;
import java.util.Optional;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {

    @InjectMocks
    ConsultarPetShopService service;

    @Mock
    PetShopRepository repository;

    @Test
    public void consultarTest() {
        var petSHop = GenericFixture.generate(PetShop.class);
        when(repository.findById(anyLong())).thenReturn(Optional.of(petSHop));
        var result = service.consultar(RandomUtils.nextLong());
        assertNotNull(result);
        assertEquals(petSHop, result);
    }

    @Test
    public void consultarErrorTest() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> service.consultar(RandomUtils.nextLong()));
    }

}