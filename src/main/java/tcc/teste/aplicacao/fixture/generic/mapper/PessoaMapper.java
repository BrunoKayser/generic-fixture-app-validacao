package tcc.teste.aplicacao.fixture.generic.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tcc.teste.aplicacao.fixture.generic.domain.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.request.PessoaRequest;
import tcc.teste.aplicacao.fixture.generic.response.PessoaInseridaResponse;
import tcc.teste.aplicacao.fixture.generic.response.PessoaResponse;

@Component
@RequiredArgsConstructor
public class PessoaMapper {

    private final ModelMapper modelMapper;

    public Pessoa toPessoa(PessoaRequest pessoaRequest) {
        return modelMapper.map(pessoaRequest, Pessoa.class);
    }

    public PessoaResponse toPessoaResponse(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaResponse.class);
    }

    public PessoaInseridaResponse toPessoaInseridaResponse(PessoaInserida pessoaInserida) {
        return modelMapper.map(pessoaInserida, PessoaInseridaResponse.class);
    }

}
