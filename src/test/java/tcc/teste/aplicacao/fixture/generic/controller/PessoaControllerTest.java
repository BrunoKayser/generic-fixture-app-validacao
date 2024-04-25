package tcc.teste.aplicacao.fixture.generic.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {


    @InjectMocks
    PessoaController pessoaController;

    @Mock
    PessoaMapper pessoaMapper;

    @Mock
    InserirPessoaService inserirPessoaService;

    @Mock
    ConsultarPessoaService consultarPessoaService;

    @Test
    void shoulInsertWithSuccess() {

        Mockito.when(inserirPessoaService.inserir(Mockito.any())).thenReturn(new PessoaInserida(1L));
        Mockito.when(pessoaMapper.toPessoa(Mockito.any())).thenReturn(mockPerson());
        Mockito.when(pessoaMapper.toPessoaInseridaResponse(Mockito.any())).thenReturn(new PessoaInseridaResponse(1L));

        ResponseEntity<PessoaInseridaResponse> insertPersonResponse = pessoaController.insert(mockPersonRequest());

        Assertions.assertEquals(1L, insertPersonResponse.getBody().getId());
    }

    @Test
    void shoulConsultData() {
        Mockito.when(consultarPessoaService.consultar(Mockito.any())).thenReturn(mockPerson());
        Mockito.when(pessoaMapper.toPessoaResponse(Mockito.any())).thenReturn(new PessoaResponse());

        ResponseEntity<PessoaResponse> pessoaGet = pessoaController.getPessoa(1L);

        Assertions.assertEquals(1L, mockPerson().getId());
    }


    PessoaRequest mockPersonRequest() {

        // Dados Animal
        List<AnimalRequest> animalRequestList = new ArrayList<>();

        TipoAnimalEnum cachorro = TipoAnimalEnum.CACHORRO;
        AnimalRequest animalUm = new AnimalRequest("Zezinho", "Vira Latis", cachorro, LocalDate.now());

        animalRequestList.add(animalUm);

        // Dados Pessoa
        GeneroEnum masculino = GeneroEnum.MASCULINO;
        return new PessoaRequest("Brunao", "Lindo", "Kayser", masculino, animalRequestList, LocalDate.now());
    }

    Pessoa mockPerson() {

        // Dados Animal
        List<Animal> animalRequestList = new ArrayList<>();

        TipoAnimalEnum cachorro = TipoAnimalEnum.CACHORRO;
        Animal animalUm = new Animal(1L, "Zezinho", LocalDate.now(), "Vira Latis", TipoAnimalEnum.CACHORRO, new Pessoa());

        animalRequestList.add(animalUm);

        // Dados Pessoa
        GeneroEnum masculino = GeneroEnum.MASCULINO;
        return new Pessoa(1L, "Brunao", "Lindo", masculino, "Kayser", LocalDate.now(), animalRequestList);
    }



}