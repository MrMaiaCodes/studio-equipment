package br.com.studioequipment.apis.dto.responses.responses;

import br.com.studioequipment.apis.dto.requests.EquipmentDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipmentResponseDTO {

    public EquipmentDTO data;
}
