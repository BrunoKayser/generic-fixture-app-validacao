package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tcc.teste.aplicacao.fixture.generic.domain.animal.Animal;
import tcc.teste.aplicacao.fixture.generic.domain.enums.GeneroEnum;
import tcc.teste.aplicacao.fixture.generic.domain.enums.TipoAnimalEnum;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
class ConsultarPessoaServiceTest {

  @InjectMocks
  private ConsultarPessoaService service;

  @Mock
  private PessoaRepository pessoaRepository;

  @Test
  void deveConsultarComSucesso() {

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

    when(pessoaRepository.findById(123L))
        .thenReturn(Optional.of(pessoa));

    final var result = service.consultar(123L);

    assertNotNull(result);
    assertEquals(pessoa, result);
  }

  @Test
  void deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId() {

    when(pessoaRepository.findById(123L))
        .thenReturn(Optional.empty());

    final var ex = assertThrows(NaoEncontradoException.class, () -> service.consultar(123L));

    assertNotNull(ex.getMessage());
    assertEquals(String.format("Pessoa com %d não encontrada", 123L), ex.getMessage());


  }
}