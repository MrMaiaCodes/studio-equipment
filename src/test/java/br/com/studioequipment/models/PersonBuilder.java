package br.com.studioequipment.models;

import br.com.studioequipment.repository.entities.Customer;

public class PersonBuilder {


    public static Customer personSuccessBuilder() {
        return Customer.builder().name("jimmy").age(50L).build();
    }
}
