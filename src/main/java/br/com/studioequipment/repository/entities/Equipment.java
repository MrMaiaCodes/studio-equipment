package br.com.studioequipment.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "TB_EQUIPMENT")
@GenericGenerator(
        name = "SEQ_EQUIPMENT",
        strategy = "org.hibernate.id.enhanced.sequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_DOCUMENT"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
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

    public Long getIdNumber() {return idNumber;}

    public void setIdNumber(Long idNumber) {this.idNumber = idNumber;}

    public String getEquipmentType() {return equipmentType;}

    public void setEquipmentType(String equipmentType) {this.equipmentType = equipmentType;}

    public Long getEquipmentPrice() {return equipmentPrice;}

    public void setEquipmentPrice(Long equipmentPrice) {this.equipmentPrice = equipmentPrice;}

}
