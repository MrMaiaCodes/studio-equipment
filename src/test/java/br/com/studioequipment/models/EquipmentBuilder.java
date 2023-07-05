package br.com.studioequipment.models;

import br.com.studioequipment.repository.entities.Equipment;
import br.com.studioequipment.repository.entities.Person;

import java.util.List;

public class EquipmentBuilder {

    //@Id
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPMENT")
    //    @Column(name = "ID_EQUIPMENT")
    //    private Long idNumber;
    //
    //    @Column(name = "DT_TYPE")
    //    private String equipmentType;
    //
    //    @Column(name = "DT_NAME")
    //    private String equipmentName;
    //
    //    @Column(name = "DT_SERIALNUMBER")
    //    private String serialNumber;
    //
    //    @Column(name = "PRICE_EQUIPMENT")
    //    private Long equipmentPrice;
    //
    //    @Column(name = "OWNER")
    //    @JoinColumn(name = "PERSON_ID")
    //    private Person owner;

    public static Equipment equipmentSuccessBuilder() {
        return Equipment.builder().idNumber(1L).equipmentType("instrument")
                .equipmentName("Shredder guitar").serialNumber("1234").equipmentPrice(5000L)
                .build();
    }
}
