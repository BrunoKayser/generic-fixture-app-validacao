package tcc.teste.aplicacao.fixture.generic.mapper;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopMapperTest {

    @InjectMocks
    private PetShopMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void deveMapearPetShopRequestParaPetShopComSucesso() {
        when(modelMapper.map(any(), any())).thenReturn(GenericFixture.generate(PetShop.class));

        var result = assertDoesNotThrow(() -> mapper.toPetShop(GenericFixture.generate(PetShopRequest.class)));

        assertNotNull(result.getId());
        assertNotNull(result.getNome());
        assertNotNull(result.getEndereco());
        assertNotNull(result.getTipoPredio());
        assertNotNull(result.getDataInauguracao());
        assertNotNull(result.getFuncionarios());
    }

    @Test
    void deveMapearPetShopInseridaParaPetShopInseridaResponseComSucesso() {
        when(modelMapper.map(any(), any())).thenReturn(GenericFixture.generate(PetShopInseridaResponse.class));

        var result = assertDoesNotThrow(() -> mapper.toPetShopInseridaResponse(GenericFixture.generate(PetShopInserida.class)));

        assertNotNull(result.getId());
    }

    @Test
    void deveMapearPetShopParaPetShopResponseComSucesso() {
        when(modelMapper.map(any(), any())).thenReturn(GenericFixture.generate(PetShopResponse.class));

        var result = assertDoesNotThrow(() -> mapper.toPetShopResponse(GenericFixture.generate(PetShop.class)));

        assertNotNull(result.getNome());
        assertNotNull(result.getEndereco());
        assertNotNull(result.getTipoPredio());
        assertNotNull(result.getDataInauguracao());
        assertNotNull(result.getFuncionarios());
    }

}