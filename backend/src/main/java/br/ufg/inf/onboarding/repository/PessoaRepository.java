package br.ufg.inf.onboarding.repository;

import br.ufg.inf.onboarding.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "pessoa", collectionResourceRel = "pessoas")
@Description("Manutenção de pessoas")
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa,Integer> {

    @RestResource(path = "byCpf", rel = "searchByCpf")
    @Description("Retorna a pessoa com o CPF fornecido")
    Pessoa findPessoaByCpfEquals(String cpf);


    @Query("select p from Pessoa p where lower(p.nome) like lower(concat('%', 'Gian', '%')) order by p.nome")
    Page<Pessoa> findAll(String busca, Pageable pageable);
}
