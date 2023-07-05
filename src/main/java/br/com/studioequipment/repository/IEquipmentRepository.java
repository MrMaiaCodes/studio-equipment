package br.com.studioequipment.repository;

import br.com.studioequipment.repository.entities.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipmentRepository extends MongoRepository<Equipment, String> {

    //Equipment findEquipmentByName(String name);
}
