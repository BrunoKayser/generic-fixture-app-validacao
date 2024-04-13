package tcc.teste.aplicacao.fixture.generic.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
class PessoaMapperTest {

  @InjectMocks
  private PessoaMapper mapper;

  @Mock
  private ModelMapper modelMapper;

  @Test
  void deveMapearPessoaRequestParaPessoaComSucesso() {

    final var pessoaRequest = PessoaRequest.builder()
        .apelido("apelido")
        .nome("nome")
        .genero(GeneroEnum.MASCULINO)
        .dataNascimento(LocalDate.now().minusYears(20))
        .sobrenome("tanto faz")
        .animaisDeEstimacao(List.of(
            AnimalRequest.builder().dataNascimento(LocalDate.now().minusYears(12)).nome("doguiiinho").raca("caramelo")
                .tipoAnimal(
                    TipoAnimalEnum.CACHORRO).build()))
        .build();

    final var pessoa = Pessoa.builder()
        .apelido("apelido")
        .nome("nome")
        .genero(GeneroEnum.MASCULINO)
        .dataNascimento(LocalDate.now().minusYears(20))
        .sobrenome("tanto faz")
        .animaisDeEstimacao(List.of(
            Animal.builder().dataNascimento(LocalDate.now().minusYears(12)).nome("doguiiinho").raca("caramelo")
                .tipoAnimal(
                    TipoAnimalEnum.CACHORRO).build()))
        .build();

    when(modelMapper.map(pessoaRequest, Pessoa.class))
        .thenReturn(pessoa);

    final var result = mapper.toPessoa(pessoaRequest);

    assertNotNull(result);
    assertEquals(pessoa, result);

  }

  @Test
  void deveMapearPessoaParaPessoaResponseComSucesso() {

    final var pessoa = Pessoa.builder()
        .apelido("apelido")
        .nome("nome")
        .genero(GeneroEnum.MASCULINO)
        .dataNascimento(LocalDate.now().minusYears(20))
        .sobrenome("tanto faz")
        .animaisDeEstimacao(List.of(
            Animal.builder().dataNascimento(LocalDate.now().minusYears(12)).nome("doguiiinho").raca("caramelo")
                .tipoAnimal(
                    TipoAnimalEnum.CACHORRO).build()))
        .build();

    final var pessoaResponse = PessoaResponse.builder()
        .apelido("apelido")
        .nome("nome")
        .genero(GeneroEnum.MASCULINO)
        .dataNascimento(LocalDate.now().minusYears(20))
        .sobrenome("tanto faz")
        .animaisDeEstimacao(List.of(
            AnimalResponse.builder().dataNascimento(LocalDate.now().minusYears(12)).nome("doguiiinho").raca("caramelo")
                .tipoAnimal(
                    TipoAnimalEnum.CACHORRO).build()))
        .build();

    when(modelMapper.map(pessoa, PessoaResponse.class))
        .thenReturn(pessoaResponse);

    final var result = mapper.toPessoaResponse(pessoa);

    assertNotNull(result);
    assertEquals(pessoaResponse, result);

  }

  @Test
  void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {

    final var pessoaInserida = PessoaInserida.builder()
        .id(123L)
        .build();

    final var pessoaInseridaResponse = PessoaInseridaResponse.builder()
        .id(123L)
        .build();

    when(modelMapper.map(pessoaInserida, PessoaInseridaResponse.class))
        .thenReturn(pessoaInseridaResponse);

    final var result = mapper.toPessoaInseridaResponse(pessoaInserida);

    assertNotNull(result);
    assertEquals(pessoaInseridaResponse, result);

  }
}