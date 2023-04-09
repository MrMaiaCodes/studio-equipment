package br.com.studioequipment.service.impl;

import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.IPersonRepository;
import br.com.studioequipment.repository.entities.Person;
import br.com.studioequipment.service.AbstractValidateService;
import br.com.studioequipment.service.interfaces.IEquipmentService;
import br.com.studioequipment.service.interfaces.IPersonService;
import br.com.studioequipment.service.interfaces.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PersonService extends AbstractValidateService<Person> implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IEquipmentService equipmentService;

    @Override
    public Person save(Person person) throws SaveMethodException {
        log.info("initialized PersonService.save");
        if (validate(person)) {
            log.info("Processing save");
            personRepository.save(person);
            log.info("save complete");
            return person;
        } else {
            log.error("validation failed");
            throw new SaveMethodException("P01", "Invalid Person saved");
        }
    }

    @Override
    public void delete(Person person) throws PersonNotFoundException, EquipmentNotFoundException {
        log.info("initialized PersonService.delete");
        var personDelete = personRepository.findById(person.getId()).orElseThrow(
                () -> new PersonNotFoundException("P01", "Person Not Found")
        );
        log.info("Processing delete");
        personRepository.delete(personDelete);
        log.info("delete complete");
    }

    @Override
    public List<Person> listAll() {
        log.info("initialized PersonService.listAll");
        log.info("listAll complete");
        return personRepository.findAll();
    }

    @Override
    public Person update(Person person) throws PersonNotFoundException, EquipmentNotFoundException {
        log.info("initialized PersonService.update");
        var personFind = personRepository.findById(person.getId());
        var personUpdate = personFind.orElseThrow(
                () -> new PersonNotFoundException("P01", "Person Not Found")
        );

        log.info("processing update");
        personUpdate.setName(person.getName());
        personUpdate.setAge(person.getAge());
        personUpdate.setId(person.getId());

        log.info("update complete");
        return personRepository.save(personUpdate);
    }

    @Override
    public List<Person> findPersonByName(String personName) throws PersonNotFoundException {
        log.info("initialized PersonService.findPersonByName");
        var personFind = personRepository.findPersonByName(personName);
        if (personFind != null) {
            log.info("processing findPersonByName");
            log.info("findPersonByName complete");
            return personFind;
        } else {
            log.error("findPersonByName failed");
            throw new PersonNotFoundException("P02", "Error in finding " + personName + ".");
        }
    }

    @Override
    protected boolean validate(Person person) {
        return !validateStringIsNullOrBlank(person.getName())
                && validateLongNotZero(person.getAge());
    }

    @Override
    public void addEquipment(String personName, String equipmentSerialNumber)
        throws PersonNotFoundException, EquipmentNotFoundException {
        log.info("initialized personService.addEquipment");
        var personFind = findPersonByName(personName);
        var equipmentFind = equipmentService
                .findEquipmentBySerialNumber(equipmentSerialNumber);
        log.info("processing add");
        if (personFind.get(0).getEquipments() == null)
            personFind.get(0).setEquipments(new ArrayList<>());
        personFind.get(0).getEquipments().add(equipmentFind);
        personRepository.save(personFind.get(0));
        log.info("add complete");
    }
}
