package tcc.teste.aplicacao.fixture.mapper;

import generic.fixture.GenericFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PetShopMapper;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetShopMapperTest {

    PetShopMapper petShopMapper = new PetShopMapper(new ModelMapper());

    @Test
    @DisplayName("Deve mapear PetShopRequest para PetShop com sucesso")
    void deveMapearPetShopRequestParaPetShopComSucesso() {
        PetShopRequest petShopRequest = GenericFixture.generate(PetShopRequest.class, 2);
        PetShop petShop = petShopMapper.toPetShop(petShopRequest);

        assertEquals(petShopRequest.getNome(), petShop.getNome());
        assertEquals(petShopRequest.getDataInauguracao(), petShop.getDataInauguracao());
        assertEquals(petShopRequest.getFuncionarios().size(), petShop.getFuncionarios().size());
        assertEquals(petShopRequest.getEndereco(), petShop.getEndereco());
        assertEquals(petShopRequest.getTipoPredio(), petShop.getTipoPredio());

        for (int i = 0; i < petShopRequest.getFuncionarios().size(); i++) {
            assertEquals(petShopRequest.getFuncionarios().get(i).getNome(), petShop.getFuncionarios().get(i).getNome());
            assertEquals(petShopRequest.getFuncionarios().get(i).getSobrenome(), petShop.getFuncionarios().get(i).getSobrenome());
            assertEquals(petShopRequest.getFuncionarios().get(i).getGenero(), petShop.getFuncionarios().get(i).getGenero());
            assertEquals(petShopRequest.getFuncionarios().get(i).getDataNascimento(), petShop.getFuncionarios().get(i).getDataNascimento());
        }
    }

    @Test
    @DisplayName("Deve mapear PetShopInserida para PetShopInseridaResponse com sucesso")
    void deveMapearPetShopInseridaParaPetShopInseridaResponseComSucesso() {
        PetShopInserida petShopInserida = GenericFixture.generate(PetShopInserida.class, 2);
        PetShopInseridaResponse petShopInseridaResponse = petShopMapper.toPetShopInseridaResponse(petShopInserida);

        assertEquals(petShopInserida.getId(), petShopInseridaResponse.getId());
    }

    @Test
    @DisplayName("Deve mapear PetShop para PetShopResponse com sucesso")
    void deveMapearPetShopParaPetShopResponseComSucesso() {
        PetShop petShop = GenericFixture.generate(PetShop.class, 2);
        PetShopResponse petShopResponse = petShopMapper.toPetShopResponse(petShop);

        assertEquals(petShop.getNome(), petShopResponse.getNome());
        assertEquals(petShop.getDataInauguracao(), petShopResponse.getDataInauguracao());
        assertEquals(petShop.getFuncionarios().size(), petShopResponse.getFuncionarios().size());
        assertEquals(petShop.getEndereco(), petShopResponse.getEndereco());
        assertEquals(petShop.getTipoPredio(), petShopResponse.getTipoPredio());

        for (int i = 0; i < petShop.getFuncionarios().size(); i++) {
            assertEquals(petShop.getFuncionarios().get(i).getNome(), petShopResponse.getFuncionarios().get(i).getNome());
            assertEquals(petShop.getFuncionarios().get(i).getSobrenome(), petShopResponse.getFuncionarios().get(i).getSobrenome());
            assertEquals(petShop.getFuncionarios().get(i).getGenero(), petShopResponse.getFuncionarios().get(i).getGenero());
            assertEquals(petShop.getFuncionarios().get(i).getDataNascimento(), petShopResponse.getFuncionarios().get(i).getDataNascimento());
        }
    }
}
