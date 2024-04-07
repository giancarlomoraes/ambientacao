package br.ufg.inf.onboarding.service;

import br.ufg.inf.onboarding.exception.CustomException;
import br.ufg.inf.onboarding.model.Pessoa;
import br.ufg.inf.onboarding.repository.PessoaRepository;
import br.ufg.inf.onboarding.validators.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {

        if(Optional.ofNullable(pessoa.getNome()).isEmpty()){
            throw CustomException.badRequest("Nome é obrigatório");
        }
        if(Optional.ofNullable(pessoa.getCpf()).isEmpty()){
            throw CustomException.badRequest("CPF é obrigatório");
        }

        if(!ValidationUtils.cpfValido(pessoa.getCpf())){
            throw CustomException.badRequest("CPF inválido");
        }

        pessoa.setCpf(pessoa.getCpf().replaceAll("[.-]", ""));

        if(pessoaRepository.findPessoaByCpfEquals(pessoa.getCpf()) != null){
            throw CustomException.badRequest("CPF já cadastrado");
        }

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
