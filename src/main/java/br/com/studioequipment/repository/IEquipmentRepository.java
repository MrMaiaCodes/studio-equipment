package br.com.studioequipment.repository;

import br.com.studioequipment.repository.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query(value = "select d from Equipment d where d.serialNumber = :serialNumber")
    Equipment findEquipmentBySerialNumber(String serialNumber);
}
