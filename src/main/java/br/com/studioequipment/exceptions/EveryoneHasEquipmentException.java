package br.com.studioequipment.exceptions;

public class EveryoneHasEquipmentException extends Exception {

    private String code;

    private String message;

    public EveryoneHasEquipmentException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() { return code; }
}
