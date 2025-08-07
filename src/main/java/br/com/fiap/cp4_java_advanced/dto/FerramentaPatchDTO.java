package br.com.fiap.cp4_java_advanced.dto;

import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FerramentaPatchDTO {

    private String nome;

    private String tipo;

    private String classificacao;

    private Tamanho tamanho;

    private BigDecimal preco;

    private Integer quantidade;
}
