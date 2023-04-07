package br.com.studioequipment.service.interfaces;

import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.repository.entities.Equipment;

public interface IEquipmentService extends IService<Equipment> {

    Equipment findEquipmentBySerialNumber(String serialNumber) throws EquipmentNotFoundException;
}
