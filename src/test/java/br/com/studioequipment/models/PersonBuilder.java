package br.com.studioequipment.models;

import br.com.studioequipment.repository.entities.Person;

public class PersonBuilder {


    public static Person personSuccessBuilder() {
        return Person.builder().name("jimmy").age(50L).build();
    }
}
