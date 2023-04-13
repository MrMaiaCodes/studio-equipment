package br.com.studioequipment.adapters;

import br.com.studioequipment.apis.dto.requests.EquipmentDTO;
import br.com.studioequipment.repository.entities.Equipment;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentAdapter {

    public static Equipment convertTo(EquipmentDTO equipmentDTO) {
        return Equipment.builder()
                .equipmentType(equipmentDTO.getEquipmentType())
                .equipmentName(equipmentDTO.getEquipmentName())
                .equipmentPrice(equipmentDTO.getEquipmentPrice())
                .serialNumber(equipmentDTO.getSerialNumber())
                .build();
    }

    public static List<Equipment> convertToList(List<EquipmentDTO> equipmentDTO) {
        return equipmentDTO.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }
}
