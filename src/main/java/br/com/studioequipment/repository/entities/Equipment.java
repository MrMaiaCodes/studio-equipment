package br.com.studioequipment.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "TB_EQUIPMENT")
@SequenceGenerator(name = "SEQ_EQUIPMENT")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPMENT")
    @Column(name = "ID_EQUIPMENT")
    private Long idNumber;

    @Column(name = "DT_TYPE")
    private String equipmentType;

    @Column(name = "DT_NAME")
    private String equipmentName;

    @Column(name = "DT_SERIALNUMBER")
    private String serialNumber;

    @Column(name = "PRICE_EQUIPMENT")
    private Long equipmentPrice;

    @Column(name = "OWNER")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Person owner;

    public Long getIdNumber() {return idNumber;}

    public void setIdNumber(Long idNumber) {this.idNumber = idNumber;}

    public String getEquipmentType() {return equipmentType;}

    public void setEquipmentType(String equipmentType) {this.equipmentType = equipmentType;}

    public Long getEquipmentPrice() {return equipmentPrice;}

    public void setEquipmentPrice(Long equipmentPrice) {this.equipmentPrice = equipmentPrice;}

}
