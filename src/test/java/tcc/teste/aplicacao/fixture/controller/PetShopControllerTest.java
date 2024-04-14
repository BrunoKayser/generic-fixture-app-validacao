package tcc.teste.aplicacao.fixture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import generic.fixture.GenericFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import tcc.teste.aplicacao.fixture.generic.Application;
import tcc.teste.aplicacao.fixture.generic.domain.pet.shop.PetShop;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;
import tcc.teste.aplicacao.fixture.generic.repository.PetShopRepository;
import tcc.teste.aplicacao.fixture.generic.request.PetShopRequest;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {Application.class})
class PetShopControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PetShopRepository petShopRepository;

    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Deve inserir com sucesso")
    void deveInserirComSucesso() throws Exception {
        PetShopRequest petShop = GenericFixture.generate(PetShopRequest.class, 2);
        mockMvc.perform(post("/v1/pet-shop")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(petShop)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("Deve consultar com sucesso")
    void deveConsultarComSucesso() throws Exception {
        var result = petShopRepository.save(GenericFixture.generate(PetShop.class, 2));
        var formattedDate = result.getDataInauguracao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        mockMvc.perform(get("/v1/pet-shop/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(result.getNome()))
                .andExpect(jsonPath("$.endereco").value(result.getEndereco()))
                .andExpect(jsonPath("$.tipoPredio").value(result.getTipoPredio().name()))
                .andExpect(jsonPath("$.dataInauguracao").value(formattedDate))
                .andExpect(jsonPath("$.funcionarios").isArray())
                .andExpect(jsonPath("$.funcionarios").isEmpty());
    }
}
