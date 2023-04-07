package br.com.studioequipment.service.impl;

import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.IEquipmentRepository;
import br.com.studioequipment.repository.entities.Equipment;
import br.com.studioequipment.service.interfaces.IEquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EquipmentService implements IEquipmentService {

    @Autowired
    private IEquipmentRepository equipmentRepository;

    @Override
    public Equipment findEquipmentBySerialNumber(String serialNumber) throws EquipmentNotFoundException {
        log.info("initialized find");
        var equipmentFind = equipmentRepository.findEquipmentBySerialNumber(serialNumber);
        if (equipmentFind != null) {
            log.info("processing find");
            log.info("find completed");
            return equipmentFind;
        } else {
            log.error("equipment not found");
            throw new EquipmentNotFoundException("E01", "Error finding equipment");
        }
    }

    @Override
    public Equipment save(Equipment equipment) throws SaveMethodException {
        log.info("initialized equipmentService.save");
        if (equipment != null) {
            log.info("processing save");
            equipmentRepository.save(equipment);
            log.info("save complete");
            return equipment;
        } else {
            log.error("validation failed");
            throw new SaveMethodException("E01", "invalid Equipment entered");
        }
    }

    @Override
    public void delete(Equipment equipment) throws EquipmentNotFoundException {
        log.info("initialized equipmentService.delete");
        var equipmentDelete = equipmentRepository.findById(
                equipment.getIdNumber()).orElseThrow(() ->
                new EquipmentNotFoundException("E01", "Equipment not found"));
        log.info("processing delete");
        equipmentRepository.delete(equipmentDelete);
        log.info("delete completed");
    }

    @Override
    public List<Equipment> listAll() {
        log.info("initialized EquipmentService.listAll");
        log.info("listAll completed");
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment update(Equipment equipment) throws EquipmentNotFoundException {

        log.info("initialized EquipmentService.update");
        var equipmentFind = equipmentRepository.findById(equipment.getIdNumber())
                .orElseThrow(() -> new EquipmentNotFoundException("E01", "Equipment not found")
                );
        log.info("processing update");
        equipmentFind.setEquipmentName(equipment.getEquipmentName());
        equipmentFind.setEquipmentType(equipment.getEquipmentType());
        equipmentFind.setEquipmentPrice(equipment.getEquipmentPrice());

        log.info("update complete");

        return equipmentRepository.save(equipmentFind);
    }
}
