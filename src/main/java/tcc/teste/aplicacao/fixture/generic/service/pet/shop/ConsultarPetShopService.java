package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

@Service
@RequiredArgsConstructor
public class ConsultarPetShopService {

    private final PetShopRepository petShopRepository;

    public PetShop consultar(Long id) {

        return petShopRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(String.format("PetShop com %d não encontrada", id)));
    }

}
