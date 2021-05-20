package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros de sessão não existente.
 */
public class SessaoInexistenteException extends RuntimeException {
    public SessaoInexistenteException(String message) {
        super(message);
    }
}
