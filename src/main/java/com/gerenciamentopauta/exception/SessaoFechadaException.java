package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros de sessão ja fechada.
 */
public class SessaoFechadaException extends RuntimeException {
    public SessaoFechadaException(String message) {
        super(message);
    }
}
