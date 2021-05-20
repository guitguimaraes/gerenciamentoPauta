package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros de sessão ja aberta.
 */
public class SessaoAbertaException extends RuntimeException {
    public SessaoAbertaException(String message) {
        super(message);
    }
}
