package br.ufg.inf.onboarding.service;

import br.ufg.inf.onboarding.model.Pessoa;
import br.ufg.inf.onboarding.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;


    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> findById(Integer id) {
        return pessoaRepository.findById(id);
    }

    public Iterable<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa findByCpf(String cpf) {
        return pessoaRepository.findPessoaByCpfEquals(cpf);
    }

    public void deleteById(Integer id) {
        pessoaRepository.deleteById(id);
    }

    // Adicione outros métodos conforme necessário
}
