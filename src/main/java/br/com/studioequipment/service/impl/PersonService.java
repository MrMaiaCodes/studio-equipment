package br.com.studioequipment.service.impl;

import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.IPersonRepository;
import br.com.studioequipment.repository.entities.Customer;
import br.com.studioequipment.service.AbstractValidateService;
import br.com.studioequipment.service.interfaces.IEquipmentService;
import br.com.studioequipment.service.interfaces.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PersonService extends AbstractValidateService<Customer> implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Customer save(Customer customer) throws SaveMethodException, PersonNotFoundException {
        log.info("initialized PersonService.save");
        if (validate(customer)) {
            log.info("Processing save");
            isOverage(customer);
            personRepository.save(customer);
            log.info("save complete");
            return customer;
        } else {
            log.error("validation failed");
            throw new SaveMethodException("P01", "Invalid Person saved");
        }
    }


/*
    public Customer saveSimplified(PersonDTO personDTO){
        var url = "https://viacep.com.br/ws/" + personDTO.getZipCode() + "/json/";
        var httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Address> httpEntity = new HttpEntity<>(httpHeaders);
        var result = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity, Address.class);
        var newPerson = Customer.builder().name(personDTO.getName())
                .address(result.getBody()).build();
        return personRepository.save(newPerson);

    }

 */

    @Override
    public void delete(Customer customer) throws PersonNotFoundException, EquipmentNotFoundException {
        log.info("initialized PersonService.delete");
        var personDelete = personRepository.findById(customer.getId()).orElseThrow(
                () -> new PersonNotFoundException("P01", "Person Not Found")
        );
        log.info("Processing delete");
        personRepository.delete(personDelete);
        log.info("delete complete");
    }

    @Override
    public List<Customer> listAll() {
        log.info("initialized PersonService.listAll");
        log.info("listAll complete");
        return personRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) throws PersonNotFoundException, EquipmentNotFoundException {
        log.info("initialized PersonService.update");
        var personFind = personRepository.findById(customer.getId());
        var personUpdate = personFind.orElseThrow(
                () -> new PersonNotFoundException("P01", "Person Not Found")
        );

        log.info("processing update");
        personUpdate.setName(customer.getName());
        personUpdate.setAge(customer.getAge());
        personUpdate.setId(customer.getId());

        log.info("update complete");
        return personRepository.save(personUpdate);
    }

        @Override
    public List<Customer> findPersonByName(String personName) throws PersonNotFoundException {
        log.info("initialized PersonService.findPersonByName");
        var personFind = Arrays.asList(new Customer());
            //personRepository.findPersonByName(personName);
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
    protected boolean validate(Customer customer) {
        return !validateStringIsNullOrBlank(customer.getName())
                && validateLongNotZero(customer.getAge());
    }

    @Override
    public void addEquipment(String personName, String equipmentName)
        throws PersonNotFoundException, EquipmentNotFoundException {
        log.info("initialized personService.addEquipment");
        var personFind = findPersonByName(personName);
        var equipmentFind = equipmentService
                .findEquipmentByName(equipmentName);
        log.info("processing add");
        if (personFind.get(0).getEquipments() == null)
            personFind.get(0).setEquipments(new ArrayList<>());
        personFind.get(0).getEquipments().add(equipmentFind);
        equipmentFind.setOwner(personFind.get(0));
        personRepository.save(personFind.get(0));
        log.info("add complete");
    }

    @Override
    public void isOverage(Customer customer) throws PersonNotFoundException {
        if (customer.getAge() >= 18) {
            customer.isOverage();
        }
    }
}
