package tcc.teste.aplicacao.fixture.generic.controller.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.PessoaInseridaFixture;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.PessoaRequestFixture;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaMapperTest {

    private static PessoaMapper pessoaMapper;

    @BeforeAll
    static void setUp() {
        pessoaMapper = new PessoaMapper(new ModelMapper());
    }

    @Test
    @DisplayName("Deve mapear PessoaRequest para Pessoa com sucesso")
    void deveMapearPessoaRequestParaPessoaComSucesso() {
        var request = PessoaRequestFixture.pessoaRequestComAnimais(5);
        var pessoa = pessoaMapper.toPessoa(request);

        assertEquals(request.getNome(), pessoa.getNome());
        assertEquals(request.getApelido(), pessoa.getApelido());
        assertEquals(request.getGenero(), pessoa.getGenero());
        assertEquals(request.getSobrenome(), pessoa.getSobrenome());
        assertEquals(request.getDataNascimento(), pessoa.getDataNascimento());
        assertEquals(request.getAnimaisDeEstimacao().size(), pessoa.getAnimaisDeEstimacao().size());
    }

    @Test
    @DisplayName("Deve mapear Pessoa para PessoaResponse com sucesso")
    void deveMapearPessoaParaPessoaResponseComSucesso() {
        var pessoa = PessoaFixture.pessoaComAnimal(5);
        var response = pessoaMapper.toPessoaResponse(pessoa);

        assertEquals(pessoa.getNome(), response.getNome());
        assertEquals(pessoa.getApelido(), response.getApelido());
        assertEquals(pessoa.getGenero(), response.getGenero());
        assertEquals(pessoa.getSobrenome(), response.getSobrenome());
        assertEquals(pessoa.getDataNascimento(), response.getDataNascimento());
        assertEquals(pessoa.getAnimaisDeEstimacao().size(), response.getAnimaisDeEstimacao().size());
    }

    @Test
    @DisplayName("Deve mapear PessoaInserida para PessoaInseridaResponse com sucesso")
    void deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso() {
        var pessoaInserida = PessoaInseridaFixture.pessoaInserida();
        var response = pessoaMapper.toPessoaInseridaResponse(pessoaInserida);

        assertEquals(pessoaInserida.getId(), response.getId());
    }
}
