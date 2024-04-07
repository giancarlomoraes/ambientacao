package br.ufg.inf.onboarding.service;

import br.ufg.inf.onboarding.exception.CustomException;
import br.ufg.inf.onboarding.model.Pessoa;
import br.ufg.inf.onboarding.repository.PessoaRepository;
import br.ufg.inf.onboarding.util.paginacao.Pageable;
import br.ufg.inf.onboarding.util.paginacao.Paginated;
import br.ufg.inf.onboarding.validators.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Pessoa editarPessoa(Pessoa pessoa) {

        if(Optional.ofNullable(pessoa.getId()).isEmpty()){
            throw CustomException.badRequest("Id é obrigatório");
        }

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

        if(pessoaRepository.findPessoaByCpfEquals(pessoa.getCpf(), pessoa.getId()) != null){
            throw CustomException.badRequest("CPF já cadastrado");
        }

        return pessoaRepository.save(pessoa);
    }

    public Pessoa findById(Integer id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if(pessoa.isEmpty()){
            throw CustomException.badRequest("Usuário não encontrado");
        }

        return pessoa.get();
    }

    public Paginated getAll(int pagina, int tamanhoPagina, String busca) {

        Pageable pageable = new Pageable(pagina, tamanhoPagina);

        Page<Pessoa> pessoas = pessoaRepository.findAll(busca, PageRequest.of(pageable.getPagina(), pageable.getTamanhoPagina()));

        return Pageable.of(pessoas.getTotalElements(), pageable, pessoas.getContent().stream().map(pessoa -> new Pessoa(
                pessoa.getId(), pessoa.getNome(), pessoa.getCpf())).toList());
    }

    public Pessoa findByCpf(String cpf) {
        return pessoaRepository.findPessoaByCpfEquals(cpf);
    }

    public void deleteById(Integer id) {
        pessoaRepository.deleteById(id);
    }

    // Adicione outros métodos conforme necessário
}
