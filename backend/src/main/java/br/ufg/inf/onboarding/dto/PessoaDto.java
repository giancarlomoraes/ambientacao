package br.ufg.inf.onboarding.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PessoaDto {


    private Integer id;

    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "CPF não pode estar em branco")
    private String cpf;

}
