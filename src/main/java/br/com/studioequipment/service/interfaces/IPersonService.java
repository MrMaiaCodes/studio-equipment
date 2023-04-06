package br.com.studioequipment.service.interfaces;

import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.repository.entities.Person;

import java.util.List;

public interface IPersonService extends IService<Person> {

    List<Person> findPersonByName(String personName) throws PersonNotFoundException;
}
