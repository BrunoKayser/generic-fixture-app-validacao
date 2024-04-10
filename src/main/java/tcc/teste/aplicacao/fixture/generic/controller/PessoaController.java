package tcc.teste.aplicacao.fixture.generic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.teste.aplicacao.fixture.generic.mapper.PessoaMapper;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.pessoa.PessoaResponse;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.ConsultarPessoaService;
import tcc.teste.aplicacao.fixture.generic.service.pessoa.InserirPessoaService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/pessoa")
public class PessoaController {

    private final InserirPessoaService inserirPessoaService;
    private final ConsultarPessoaService consultarPessoaService;
    private final PessoaMapper pessoaMapper;

    @PostMapping
    public ResponseEntity<PessoaInseridaResponse> insert(@RequestBody PessoaRequest pessoaRequest) {
        var pessoaInserida = inserirPessoaService.inserir(pessoaMapper.toPessoa(pessoaRequest));
        return new ResponseEntity<>(pessoaMapper.toPessoaInseridaResponse(pessoaInserida), HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PessoaResponse> getPessoa(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaMapper.toPessoaResponse(consultarPessoaService.consultar(id)));
    }

}
