package tcc.teste.aplicacao.fixture.generic.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;

@Component
@RequiredArgsConstructor
public class PetShopMapper {

    private final ModelMapper modelMapper;


    public PetShop toPetShop(PetShopRequest petShopRequest) {
        return modelMapper.map(petShopRequest, PetShop.class);
    }


    public PetShopInseridaResponse toPetShopInseridaResponse(PetShopInserida petShopInserida) {
        return modelMapper.map(petShopInserida, PetShopInseridaResponse.class);
    }

    public PetShopResponse toPetShopResponse(PetShop petShop) {
        return modelMapper.map(petShop, PetShopResponse.class);
    }
}
