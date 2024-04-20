package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import static generic.fixture.GenericFixture.generate;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
    private ConsultarPetShopService consultarPetShopService;

    @Mock
    private PetShopRepository repository;

    @Test
    void deveConsultarComSucesso() {
        var exp = generate(PetShop.class);
        when(repository.findById(any()))
            .thenReturn(Optional.of(exp));

        var result = consultarPetShopService.consultar(nextLong());

        assertEquals(exp, result);
    }

    @Test
    void deveLancarExceptionQuandoNaoEncontrarPetShopPeloId() {
        when(repository.findById(any()))
            .thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class,
            () -> consultarPetShopService.consultar(nextLong()));
    }
}