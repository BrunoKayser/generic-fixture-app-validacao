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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarPetShopServiceTest {

    @InjectMocks
    private ConsultarPetShopService service;

    @Mock
    private PetShopRepository repository;

    @Test
    void deveConsultarComSucesso() {
        when(repository.findById(any())).thenReturn(Optional.of(GenericFixture.generate(PetShop.class)));

        var result = assertDoesNotThrow(() -> service.consultar(1L));

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getNome());
        assertNotNull(result.getEndereco());
        assertNotNull(result.getTipoPredio());
        assertNotNull(result.getDataInauguracao());
        assertNotNull(result.getFuncionarios());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.consultar(1L));
    }

}