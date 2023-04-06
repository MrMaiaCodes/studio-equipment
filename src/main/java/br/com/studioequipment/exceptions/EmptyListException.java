package br.com.studioequipment.exceptions;

public class EmptyListException extends Exception {

    private String code;

    private String message;

    public EmptyListException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() { return code;}
}
