package tcc.teste.aplicacao.fixture.generic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tcc.teste.aplicacao.fixture.generic.domain.Pessoa;
import tcc.teste.aplicacao.fixture.generic.exceptions.NaoEncontradoException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

@Service
@RequiredArgsConstructor
public class ConsultarPessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa consultar(Long id) {

        return pessoaRepository.findById(id)
                .orElseThrow(() -> new  NaoEncontradoException(String.format("Pessoa com %d não encontrada", id)));
    }

}
