package tcc.teste.aplicacao.fixture.generic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import generic.fixture.GenericFixture;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.ConsultarPetShopService;

@ExtendWith(MockitoExtension.class)
public class ConsultarPetShopServiceTest {

    @InjectMocks
    private ConsultarPetShopService consultarPetShopService;

    @Mock
    private PetShopRepository petShopRepository;

    @Test
    public void deveConsultarComSucesso() {
        var petShop = GenericFixture.generate(PetShop.class);
        Mockito.when(petShopRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(petShop));
        var resposta = consultarPetShopService.consultar(1l);
        assertEquals(petShop, resposta);
    }

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId() {
        Mockito.when(petShopRepository.findById(Mockito.any())).thenThrow(NaoEncontradoException.class);

        assertThrows(NaoEncontradoException.class, () -> consultarPetShopService.consultar(1l));
    }
}
