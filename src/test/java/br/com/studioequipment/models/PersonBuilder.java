package br.com.studioequipment.models;

import br.com.studioequipment.apis.dto.requests.PersonDTO;
import br.com.studioequipment.repository.entities.Person;

public class PersonBuilder {


    public static Person person1SuccessBuilder() {
        return Person.builder().name("jimmy").age(50L).build();
    }
}
