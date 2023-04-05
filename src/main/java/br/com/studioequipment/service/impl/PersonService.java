package br.com.studioequipment.service.impl;

import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.IPersonRepository;
import br.com.studioequipment.repository.entities.Person;
import br.com.studioequipment.service.AbstractValidateService;
import br.com.studioequipment.service.interfaces.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService extends AbstractValidateService<Person> implements IService<Person> {

    @Autowired
    private IPersonRepository personRepository;

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
    protected boolean validate(Person person) {
        return !validateStringIsNullOrBlank(person.getName())
                && validateLongNotZero(person.getAge());
    }
}
