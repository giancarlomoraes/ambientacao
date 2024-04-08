package br.ufg.inf.onboarding.service;

import br.ufg.inf.onboarding.model.Pessoa;
import br.ufg.inf.onboarding.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvarPessoaTest() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste Nome");
        pessoa.setCpf("856.141.649-10");

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa savedPessoa = pessoaService.criarPessoa(pessoa);

        assertNotNull(savedPessoa);
        assertEquals("Teste Nome", savedPessoa.getNome());
        verify(pessoaRepository).save(any(Pessoa.class));
    }
}

