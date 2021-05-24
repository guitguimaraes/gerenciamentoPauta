package com.gerenciamentopauta.exception;

/**
 * Exceção para tratar erros de duracao da sessao.
 */
public class DuracaoSessaoException extends RuntimeException {
    public DuracaoSessaoException(String message) {
        super(message);
    }
}
