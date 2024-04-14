package tcc.teste.aplicacao.fixture.generic.controller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.PessoaFixture;
import tcc.teste.aplicacao.fixture.generic.controller.fixtures.PessoaRequestFixture;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaRepository pessoaRepository;

    private static ObjectMapper objectMapper;


    @BeforeAll
    public static void setUpAll() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Deve inserir com sucesso")
    void deveInserirComSucesso() throws Exception {
        mockMvc.perform(post("/v1/pessoa")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(PessoaRequestFixture.pessoaRequest())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    @DisplayName("Deve consultar com sucesso")
    void deveConsultarComSucesso() throws Exception {
        var pessoa = pessoaRepository.save(PessoaFixture.pessoaComAnimal(1));
        String formattedDate = pessoa.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/pessoa/{id}", pessoa.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(pessoa.getNome()))
                .andExpect(jsonPath("$.apelido").value(pessoa.getApelido()))
                .andExpect(jsonPath("$.genero").value(pessoa.getGenero().name()))
                .andExpect(jsonPath("$.sobrenome").value(pessoa.getSobrenome()))
                .andExpect(jsonPath("$.dataNascimento").value(formattedDate))
                .andExpect(jsonPath("$.animaisDeEstimacao").isNotEmpty())
                .andExpect(jsonPath("$.animaisDeEstimacao.size()").value(1))
                .andExpect(jsonPath("$.animaisDeEstimacao[0].nome").value(pessoa.getAnimaisDeEstimacao().get(0).getNome()))
                .andExpect(jsonPath("$.animaisDeEstimacao[0].raca").value(pessoa.getAnimaisDeEstimacao().get(0).getRaca()))
                .andExpect(jsonPath("$.animaisDeEstimacao[0].tipoAnimal").value(pessoa.getAnimaisDeEstimacao().get(0).getTipoAnimal().name()));
    }

}