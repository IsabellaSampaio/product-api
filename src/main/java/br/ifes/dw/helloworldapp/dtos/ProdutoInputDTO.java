package br.ifes.dw.helloworldapp.dtos;

import br.ifes.dw.helloworldapp.models.ProdutoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoInputDTO(@NotBlank String nome, @NotNull double preco) {
    public ProdutoInputDTO(ProdutoModel produto){
        this(produto.getNome(), produto.getPreco());
    }
}
