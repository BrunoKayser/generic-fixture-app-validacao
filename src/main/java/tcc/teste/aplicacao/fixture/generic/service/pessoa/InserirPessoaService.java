package tcc.teste.aplicacao.fixture.generic.service.pessoa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.Pessoa;
import tcc.teste.aplicacao.fixture.generic.domain.pessoa.PessoaInserida;
import tcc.teste.aplicacao.fixture.generic.exceptions.QuantidadeDeAnimaisException;
import tcc.teste.aplicacao.fixture.generic.exceptions.SomenteDeMaiorException;
import tcc.teste.aplicacao.fixture.generic.repository.PessoaRepository;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class InserirPessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaInserida inserir(Pessoa pessoa) {
        pessoaDeveSerDeMaior(pessoa);
        naoDeveTerMaisQueDoisAnimais(pessoa);

        pessoa.setPessoaNosAnimais();

        var pessoaInserida = pessoaRepository.save(pessoa);
        return new PessoaInserida(pessoaInserida.getId());
    }

    private void naoDeveTerMaisQueDoisAnimais(Pessoa pessoa) {
        if (nonNull(pessoa.getAnimaisDeEstimacao())
                && pessoa.getAnimaisDeEstimacao().size() > 2) {
            throw new QuantidadeDeAnimaisException("O Máximo de animais cadastrados por pessoa deve ser 2.");
        }

    }

    private void pessoaDeveSerDeMaior(Pessoa pessoa) {
        if (!pessoa.ehDeMaior()) {
            throw new SomenteDeMaiorException("Somente maiores de 18 anos podem realizar o cadastro.");
        }
    }

}
