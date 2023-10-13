package com.fredsonchaves07.entity;

public record Address(
        String rua,
        int numero,
        String cep,
        String cidade
) {
}
