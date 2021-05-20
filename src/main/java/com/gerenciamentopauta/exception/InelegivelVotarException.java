package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros inelegibilidade de voto.
 */
public class InelegivelVotarException extends RuntimeException {
    public InelegivelVotarException(String message) {
        super(message);
    }
}
