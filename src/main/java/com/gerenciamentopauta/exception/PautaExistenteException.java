package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros Pauta já existente.
 */
public class PautaExistenteException extends RuntimeException {
    public PautaExistenteException(String message) {
        super(message);
    }
}
