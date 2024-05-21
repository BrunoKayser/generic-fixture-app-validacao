package tcc.teste.aplicacao.fixture.generic.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.AnimalRequest;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.animal.AnimalResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;

@ExtendWith(MockitoExtension.class)
public class PessoaMapperTest {
    
    @InjectMocks
    private PessoaMapper pessoaMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearPessoaRequestParaPessoaComSucesso() {
        var pessoa = new Pessoa();

        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setSobrenome("Sobrenome");
        pessoa.setAnimaisDeEstimacao(List.of(new Animal(1l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null)));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(pessoa);

        var pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("Nome");
        pessoaRequest.setApelido("Apelido");
        pessoaRequest.setDataNascimento(LocalDate.now());
        pessoaRequest.setGenero(GeneroEnum.MASCULINO);
        pessoaRequest.setSobrenome("Sobrenome");
        pessoaRequest.setAnimaisDeEstimacao(List.of(new AnimalRequest("nome", "raca", TipoAnimalEnum.CACHORRO, LocalDate.now())));
        
        var resposta = pessoaMapper.toPessoa(pessoaRequest);

        assertEquals(resposta, pessoa);
    }

    @Test
    public void deveMapearPessoaParaPessoaResponseComSucesso() {
        var pessoaResponse = new PessoaResponse();

        pessoaResponse.setNome("Nome");
        pessoaResponse.setApelido("Apelido");
        pessoaResponse.setDataNascimento(LocalDate.now());
        pessoaResponse.setGenero(GeneroEnum.MASCULINO);
        pessoaResponse.setSobrenome("Sobrenome");
        pessoaResponse.setAnimaisDeEstimacao(List.of(new AnimalResponse("nome", "raca", TipoAnimalEnum.CACHORRO, LocalDate.now())));
        
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(pessoaResponse);

        var pessoa = new Pessoa();

        pessoa.setNome("Nome");
        pessoa.setApelido("Apelido");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setGenero(GeneroEnum.MASCULINO);
        pessoa.setSobrenome("Sobrenome");
        pessoa.setAnimaisDeEstimacao(List.of(new Animal(1l, "nome", LocalDate.now(), "raca", TipoAnimalEnum.CACHORRO, null)));

        var resposta = pessoaMapper.toPessoaResponse(pessoa);

        assertEquals(resposta, pessoaResponse);
    }

    @Test
    public void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {
        var esperado = new PessoaInseridaResponse(1l);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(esperado);

        var resposta = pessoaMapper.toPessoaInseridaResponse(new PessoaInserida(1l));

        assertEquals(esperado, resposta);
    }
}
