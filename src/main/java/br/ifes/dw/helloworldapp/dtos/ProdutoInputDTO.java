package br.ifes.dw.helloworldapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoInputDTO(@NotBlank String nome, @NotNull double preco) {
}
