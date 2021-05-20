package com.gerenciamentopauta.util;

/**
 * Enum para obter valor do voto.
 */
public enum VotoEnum {
    /**
     * Valor sim para voto.
     */
    SIM("SIM"),
    /**
     * Valor não para voto.
     */
    NAO("NÃO");

    private String value;

    VotoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
