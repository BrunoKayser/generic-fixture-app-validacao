package tcc.teste.aplicacao.fixture.generic.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private ConsultarPessoaService consultarPessoaService;

    @Mock 
    private InserirPessoaService inserirPessoaService;

    @Mock
    private PessoaMapper pessoaMapper;

    @Test
    public void deveInserirComSucesso() {
        var pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("Nome");
        pessoaRequest.setApelido("Apelido");
        pessoaRequest.setDataNascimento(LocalDate.now());
        pessoaRequest.setGenero(GeneroEnum.MASCULINO);
        pessoaRequest.setSobrenome("Sobrenome");
        pessoaRequest.setAnimaisDeEstimacao(List.of(new AnimalRequest("nome", "raca", TipoAnimalEnum.CACHORRO, LocalDate.now())));


        var pessoa = new Pessoa();

        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setSobrenome("Sobrenome");
        pessoa.setAnimaisDeEstimacao(List.of(new Animal(1l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null)));

        Mockito.when(pessoaMapper.toPessoa(pessoaRequest)).thenReturn(pessoa);
        Mockito.when(inserirPessoaService.inserir(pessoa)).thenReturn(new PessoaInserida(1l));
        Mockito.when(pessoaMapper.toPessoaInseridaResponse(Mockito.any())).thenReturn(new PessoaInseridaResponse(1l));

        var resposta = pessoaController.insert(pessoaRequest);

        assertEquals(resposta.getBody().getId(), 1l);
    }

    @Test
    public void deveConsultarComSucesso() {
        var pessoa = new Pessoa();

        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setSobrenome("Sobrenome");
        pessoa.setAnimaisDeEstimacao(List.of(new Animal(1l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null)));

        var pessoaResponse = new PessoaResponse();

        pessoaResponse.setNome("Nome");
        pessoaResponse.setApelido("Apelido");
        pessoaResponse.setDataNascimento(LocalDate.now());
        pessoaResponse.setGenero(GeneroEnum.MASCULINO);
        pessoaResponse.setSobrenome("Sobrenome");
        pessoaResponse.setAnimaisDeEstimacao(List.of(new AnimalResponse("nome", "raca", TipoAnimalEnum.CACHORRO, LocalDate.now())));

        Mockito.when(consultarPessoaService.consultar(Mockito.anyLong())).thenReturn(pessoa);
        Mockito.when(pessoaMapper.toPessoaResponse(Mockito.any())).thenReturn(pessoaResponse);

        var resposta = pessoaController.getPessoa(1l);

        assertEquals(resposta.getBody(), pessoaResponse);
    }

}
