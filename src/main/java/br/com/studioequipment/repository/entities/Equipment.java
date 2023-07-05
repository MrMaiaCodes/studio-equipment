package br.com.studioequipment.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "equipment-collection")
public class Equipment {

    @Id
    private String idNumber;

    private String equipmentType;

    private String equipmentName;

    private String serialNumber;

    private Long equipmentPrice;

    private Person owner;

}
