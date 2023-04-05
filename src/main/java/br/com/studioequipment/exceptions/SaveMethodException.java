package br.com.studioequipment.exceptions;

public class SaveMethodException extends Exception {

    private String code;

    private String message;

    public SaveMethodException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
