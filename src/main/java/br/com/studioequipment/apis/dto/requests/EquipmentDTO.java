package br.com.studioequipment.apis.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {

    private String equipmentType;

    private String serialNumber;

    private String equipmentName;

    private Long equipmentPrice;
}
