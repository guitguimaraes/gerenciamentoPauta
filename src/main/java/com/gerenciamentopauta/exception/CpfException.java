package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros de cpf.
 */
public class CpfException extends RuntimeException {
    public CpfException(String message) {
        super(message);
    }
}
