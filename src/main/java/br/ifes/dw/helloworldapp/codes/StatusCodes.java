package br.ifes.dw.helloworldapp.codes;

import lombok.Getter;

@Getter
public enum StatusCodes {
    PRODUCT_NOT_FOUND("Produto não encontrado."),
    PRODUCT_REMOVED("Produto removido com sucesso.");

    private final String code;

    StatusCodes(String code) {
        this.code = code;
    }

}
