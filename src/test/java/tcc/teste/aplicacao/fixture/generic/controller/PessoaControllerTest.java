package tcc.teste.aplicacao.fixture.generic.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
class PessoaControllerTest {

  @InjectMocks
  private PessoaController controller;

  @Mock
  private InserirPessoaService inserirPessoaService;

  @Mock
  private ConsultarPessoaService consultarPessoaService;

  @Mock
  private PessoaMapper pessoaMapper;

  @Test
  void deveInserirComSucesso() {
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

    final var pessoaInserida = PessoaInserida.builder()
        .id(123L)
        .build();

    when(pessoaMapper.toPessoa(pessoaRequest))
        .thenReturn(pessoa);

    when(inserirPessoaService.inserir(pessoaMapper.toPessoa(pessoaRequest)))
        .thenReturn(pessoaInserida);

    when(pessoaMapper.toPessoaInseridaResponse(pessoaInserida))
        .thenReturn(PessoaInseridaResponse.builder().id(123L).build());

    final var result = controller.insert(pessoaRequest);

    assertNotNull(result);
    assertEquals(123L, Objects.requireNonNull(result.getBody()).getId());

  }

  @Test
  void deveConsultarComSucesso() {

    final var pessoaId = 123L;

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

    when(consultarPessoaService.consultar(pessoaId))
        .thenReturn(pessoa);

    when(pessoaMapper.toPessoaResponse(pessoa))
        .thenReturn(pessoaResponse);

    final var result = controller.getPessoa(pessoaId);

    assertNotNull(result);
    assertEquals(pessoaResponse, result.getBody());

  }
}