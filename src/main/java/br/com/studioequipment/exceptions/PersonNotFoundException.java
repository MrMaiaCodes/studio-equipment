package br.com.studioequipment.exceptions;

public class PersonNotFoundException extends Exception {

    private String code;

    private String message;

    public PersonNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
