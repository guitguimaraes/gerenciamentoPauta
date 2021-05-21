package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros de voto já existente.
 */
public class VotoExistenteException extends RuntimeException {
    public VotoExistenteException(String message) {
        super(message);
    }
}
