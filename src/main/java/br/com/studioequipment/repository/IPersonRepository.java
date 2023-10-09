package br.com.studioequipment.repository;

import br.com.studioequipment.repository.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPersonRepository extends MongoRepository<Customer, String> {

    //List<Person> findPersonByName(String name);
}
