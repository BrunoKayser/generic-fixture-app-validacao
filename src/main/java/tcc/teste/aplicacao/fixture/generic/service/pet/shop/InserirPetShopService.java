package tcc.teste.aplicacao.fixture.generic.service.pet.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShopInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.DataInauguracaoException;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class InserirPetShopService {

    private final PetShopRepository petShopRepository;

    public PetShopInserida inserir(PetShop petShop) {

        inauguracaoNaoPodeSerNoPassado(petShop.getDataInauguracao());
        naoDeveTerMenosQueDoisFuncionarios(petShop);

        petShop.setPetShopNosFuncionarios();

        var petShopInserida = petShopRepository.save(petShop);
        return new PetShopInserida(petShopInserida.getId());

    }

    private void inauguracaoNaoPodeSerNoPassado(LocalDate dataInauguracao) {

        if (dataInauguracao.isBefore(LocalDate.now())) {
            throw new DataInauguracaoException("A data de inauguração não pode ser no passado.");
        }

    }

    private void naoDeveTerMenosQueDoisFuncionarios(PetShop petShop) {
        if (isNull(petShop.getFuncionarios())
                || petShop.getFuncionarios().size() < 2) {
            throw new QuantidadeDeAnimaisException("O Máximo de animais cadastrados por pessoa deve ser 2.");
        }

    }

}
