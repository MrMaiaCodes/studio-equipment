package br.com.studioequipment.service.interfaces;

import br.com.studioequipment.apis.dto.requests.PersonDTO;
import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.repository.entities.Customer;

import java.util.List;

public interface IPersonService extends IService<Customer> {

    List<Customer> findPersonByName(String personName) throws PersonNotFoundException;

    void addEquipment(String personName, String equipmentSerialNumber)
            throws PersonNotFoundException, EquipmentNotFoundException;

    void isOverage(Customer customer) throws PersonNotFoundException;

}
