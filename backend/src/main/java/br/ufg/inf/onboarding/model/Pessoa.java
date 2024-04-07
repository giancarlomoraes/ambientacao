package br.ufg.inf.onboarding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="PESSOA")
@AllArgsConstructor
@RequiredArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Integer id;

    private String nome;

    private String cpf;
}
