package tcc.teste.aplicacao.fixture.generic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tcc.teste.aplicacao.fixture.generic.domain.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

@Service
@RequiredArgsConstructor
public class InserirPessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaInserida inserir(Pessoa pessoa) {
        if(pessoa.ehDeMaior()) {
            setPessoaNosAnimais(pessoa);
            var pessoaInserida = pessoaRepository.save(pessoa);
            return new PessoaInserida(pessoaInserida.getId());
        } else {
            throw new SomenteDeMaiorException("Somente maiores de 18 anos podem realizar o cadastro.");
        }
    }

    private void setPessoaNosAnimais(Pessoa pessoa) {
        pessoa
                .getAnimaisDeEstimacao()
                .forEach(animal -> animal.setPessoa(pessoa));

    }

}
