package com.api.controle.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "lancamento")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class LancamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private String observacao;

    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private LocalDate datavencimento;
    @Column(name = "data_lancamento")
    @Temporal(TemporalType.DATE)
    private LocalDate datalancamento;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private PessoaModel pessoa;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categoria;
}
