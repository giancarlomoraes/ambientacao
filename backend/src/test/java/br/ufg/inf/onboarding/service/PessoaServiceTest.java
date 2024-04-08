package br.ufg.inf.onboarding.service;

import br.ufg.inf.onboarding.exception.CustomException;
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

    @Test
    void criarPessoaComCpfDuplicadoDeveLancarCustomException() {
        // Configura o repository para simular um CPF já existente
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setNome("Uma pessoa");
        pessoaExistente.setCpf("025.592.689-81");
        when(pessoaRepository.findPessoaByCpfEquals(anyString())).thenReturn(pessoaExistente);

        // Cria uma nova pessoa com o mesmo CPF para testar a criação
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome("Teste");
        novaPessoa.setCpf("025.592.689-81"); // O mesmo CPF, formatado diferente

        // Verifica se a CustomException é lançada com a mensagem esperada
        CustomException thrown = assertThrows(
                CustomException.class,
                () -> pessoaService.criarPessoa(novaPessoa),
                "Esperado CustomException ao tentar criar uma pessoa com CPF duplicado"
        );

        assertEquals("CPF já cadastrado", thrown.getDescricao());
    }
}

