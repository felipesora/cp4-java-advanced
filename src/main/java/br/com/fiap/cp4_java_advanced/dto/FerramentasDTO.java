package br.com.fiap.cp4_java_advanced.dto;

import jakarta.validation.constraints.*;

public class FerramentasDTO {

        @NotBlank(message = "nome da ferramenta é obrigatório")
        private String nome;

        @NotBlank(message = "o tipo da ferramenta é obrigatório")
        private String tipo;

        @NotBlank(message = "a classificação da ferramenta é obrigatória")
        private String classificacao;

        @NotBlank(message = "O tamanho da ferramenta é obrigatório")
        @Size(min = 2, max = 50, message = "O tamanho da ferramenta deve ter entre 2 e 50 caracteres")
        private String tamanho;

        @Min(value = 0, message = "O preço da ferramenta deve ser maior que 0")
        @NotNull(message = "O preço da ferramenta é obrigatório")
        private Double preco;
}

