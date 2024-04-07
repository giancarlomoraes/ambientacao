package br.ufg.inf.onboarding.controller;

import br.ufg.inf.onboarding.dto.PessoaDto;
import br.ufg.inf.onboarding.model.Pessoa;
import br.ufg.inf.onboarding.service.PessoaService;
import br.ufg.inf.onboarding.util.paginacao.Paginated;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;
    private final ModelMapper modelMapper;

    @PostMapping
    public PessoaDto criarPessoa(@RequestBody PessoaDto pessoaDto) {
        return modelMapper.map(pessoaService.criarPessoa(modelMapper.map(pessoaDto, Pessoa.class)), PessoaDto.class);
    }

    @GetMapping("/{id}")
    public PessoaDto findById(@PathVariable Integer id) {
        return modelMapper.map(pessoaService.findById(id), PessoaDto.class);
    }

    @GetMapping
    public ResponseEntity<Paginated> getAll(@RequestParam(defaultValue = "0") int pagina,
                                                    @RequestParam(defaultValue = "10") int tamanhoPagina,
                                                    @RequestParam(required = false, defaultValue = "") String busca
    ) {
        return ResponseEntity.ok(pessoaService.getAll(pagina, tamanhoPagina, busca));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> findByCpf(@PathVariable String cpf) {
        Pessoa pessoa = pessoaService.findByCpf(cpf);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        pessoaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}