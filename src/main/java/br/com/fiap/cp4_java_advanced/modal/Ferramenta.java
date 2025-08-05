package br.com.fiap.cp4_java_advanced.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TDS_TB_Ferramentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ferramenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 70)
    private String tipo;

    @Column(nullable = false, length = 70)
    private String classificacao;

    @Column(nullable = false, length = 50)
    private String tamanho;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double preco;
}
