package br.com.studioequipment.exceptions;

public class EquipmentNotFoundException extends Exception{

    private String code;

    private String message;

    public EquipmentNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}
