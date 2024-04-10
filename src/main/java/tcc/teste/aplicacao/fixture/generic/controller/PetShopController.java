package tcc.teste.aplicacao.fixture.generic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.teste.aplicacao.fixture.generic.mapper.PetShopMapper;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pet.shop.PetShopResponse;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.ConsultarPetShopService;
import tcc.teste.aplicacao.fixture.generic.service.pet.shop.InserirPetShopService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/pet-shop")
public class PetShopController {

    private final PetShopMapper petShopMapper;
    private final InserirPetShopService inserirPetShopService;
    private final ConsultarPetShopService consultarPetShopService;

    @PostMapping
    public ResponseEntity<PetShopInseridaResponse> insert(@RequestBody PetShopRequest petShopRequest) {
        var pessoaInserida = inserirPetShopService.inserir(petShopMapper.toPetShop(petShopRequest));
        return new ResponseEntity<>(petShopMapper.toPessoaInseridaResponse(pessoaInserida), HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PetShopResponse> getPessoa(@PathVariable Long id) {
        return ResponseEntity.ok(petShopMapper.toPessoaResponse(consultarPetShopService.consultar(id)));
    }

}
