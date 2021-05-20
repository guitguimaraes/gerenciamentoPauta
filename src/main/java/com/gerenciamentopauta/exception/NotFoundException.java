package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros entidades não encontradas.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
