package tcc.teste.aplicacao.fixture.generic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;

@Repository
public interface PetShopRepository extends JpaRepository<PetShop, Long> {
}
