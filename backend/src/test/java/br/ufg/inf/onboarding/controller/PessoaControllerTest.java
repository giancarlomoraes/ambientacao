package br.ufg.inf.onboarding.controller;

import br.ufg.inf.onboarding.dto.PessoaDto;
import br.ufg.inf.onboarding.model.Pessoa;
import br.ufg.inf.onboarding.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PessoaController.class)
class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void adicionarPessoaTest() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Teste Nome");
        pessoaDto.setCpf("856.141.649-10");

        Pessoa pessoa = new Pessoa();
        when(modelMapper.map(any(PessoaDto.class), eq(Pessoa.class))).thenReturn(pessoa);
        when(pessoaService.criarPessoa(any(Pessoa.class))).thenReturn(pessoa);
        when(modelMapper.map(any(Pessoa.class), eq(PessoaDto.class))).thenReturn(pessoaDto);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Teste Nome\",\"cpf\":\"856.141.649-10\"}"))
                .andExpect(status().isOk());

        verify(pessoaService).criarPessoa(any(Pessoa.class));
    }


}
