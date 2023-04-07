package br.com.studioequipment.repository;

import br.com.studioequipment.repository.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query(value = "select d from Equipment d where d.equipmentNumber = :equipmentNumber")
    Equipment findEquipmentBySerialNumber(String equipmentSerialNumber);
}
