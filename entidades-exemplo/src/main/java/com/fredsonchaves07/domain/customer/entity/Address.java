package com.fredsonchaves07.domain.customer.entity;

public record Address(
        String rua,
        int numero,
        String cep,
        String cidade
) {
}
