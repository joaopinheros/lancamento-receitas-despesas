package com.api.controle.model;

import com.api.controle.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pessoa")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PessoaModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Endereco endereco;

    private String nome;
    private boolean ativo;
}
