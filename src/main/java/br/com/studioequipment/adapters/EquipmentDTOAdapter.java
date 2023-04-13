package br.com.studioequipment.adapters;

import br.com.studioequipment.apis.dto.requests.EquipmentDTO;
import br.com.studioequipment.repository.entities.Equipment;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentDTOAdapter {

    public static EquipmentDTO convertTo(Equipment equipment) {

        EquipmentDTO equipmentDTO = new EquipmentDTO();

        equipmentDTO.setEquipmentType(equipment.getEquipmentType());
        equipmentDTO.setEquipmentName(equipment.getEquipmentName());
        equipmentDTO.setEquipmentPrice(equipment.getEquipmentPrice());
        equipmentDTO.setSerialNumber(equipment.getSerialNumber());

        return equipmentDTO;
    }

    public static List<EquipmentDTO> convertToList(List<Equipment> equipmentList) {
        return equipmentList.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }
}
