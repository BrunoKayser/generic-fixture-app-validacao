package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
class InserirPessoaServiceTest {

  @InjectMocks
  private InserirPessoaService service;

  @Mock
  private PessoaRepository pessoaRepository;

  @Test
  void deveInserirComSucesso() {

    final var pessoa = Pessoa.builder()
        .id(123L)
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

    when(pessoaRepository.save(pessoa))
        .thenReturn(pessoa);

    final var result = service.inserir(pessoa);

    assertNotNull(result);
    assertEquals(123L, result.getId());

  }

  @Test
  void deveLancarExceptionQuandoPessoaForMenorDeIdade() {

    final var pessoa = Pessoa.builder()
        .id(123L)
        .apelido("apelido")
        .nome("nome")
        .genero(GeneroEnum.MASCULINO)
        .dataNascimento(LocalDate.now().minusYears(7))
        .sobrenome("tanto faz")
        .animaisDeEstimacao(List.of(
            Animal.builder().dataNascimento(LocalDate.now().minusYears(12)).nome("doguiiinho").raca("caramelo")
                .tipoAnimal(
                    TipoAnimalEnum.CACHORRO).build()))
        .build();

    final var ex = assertThrows(SomenteDeMaiorException.class, () -> service.inserir(pessoa));

    assertNotNull(ex.getMessage());
    assertEquals("Somente maiores de 18 anos podem realizar o cadastro.", ex.getMessage());

    Mockito.verifyNoInteractions(pessoaRepository);
  }

  @Test
  void deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais() {

      final var pessoa = Pessoa.builder()
          .id(123L)
          .apelido("apelido")
          .nome("nome")
          .genero(GeneroEnum.MASCULINO)
          .dataNascimento(LocalDate.now().minusYears(20))
          .sobrenome("tanto faz")
          .animaisDeEstimacao(List.of(
              Animal.builder().dataNascimento(LocalDate.now().minusYears(12)).nome("doguiiinho1").raca("caramelo")
                  .tipoAnimal(
                      TipoAnimalEnum.CACHORRO).build(),
              Animal.builder().dataNascimento(LocalDate.now().minusYears(12)).nome("doguiiinho2").raca("caramelo")
                  .tipoAnimal(
                      TipoAnimalEnum.CACHORRO).build(),
              Animal.builder().dataNascimento(LocalDate.now().minusYears(12)).nome("doguiiinho3").raca("caramelo")
                  .tipoAnimal(
                      TipoAnimalEnum.CACHORRO).build()))
          .build();

      final var ex = assertThrows(QuantidadeDeAnimaisException.class, () -> service.inserir(pessoa));

      assertNotNull(ex.getMessage());
      assertEquals("O Máximo de animais cadastrados por pessoa deve ser 2.", ex.getMessage());

      Mockito.verifyNoInteractions(pessoaRepository);
    }
}