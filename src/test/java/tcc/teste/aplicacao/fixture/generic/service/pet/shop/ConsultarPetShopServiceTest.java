package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {

    @InjectMocks
    private ConsultarPetShopService tested;

    @Mock
    private PetShopRepository repository;

    @Test
    public void deveConsultarComSucesso() {
        PetShop petShop = GenericFixture.generate(PetShop.class);
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(petShop));

        var result = tested.consultar(id);
        assertEquals(result, petShop);
    }

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> tested.consultar(id));
    }
}