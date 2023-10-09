package br.com.studioequipment.exceptions;

public class MicrophoneNotFoundException extends Exception {

    private String code;

    private String message;

    public MicrophoneNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}

