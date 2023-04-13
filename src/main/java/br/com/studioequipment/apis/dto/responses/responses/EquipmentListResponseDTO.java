package br.com.studioequipment.apis.dto.responses.responses;

import br.com.studioequipment.apis.dto.requests.EquipmentDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EquipmentListResponseDTO {

    public List<EquipmentDTO> data;
}
