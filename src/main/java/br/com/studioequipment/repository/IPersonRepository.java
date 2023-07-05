package br.com.studioequipment.repository;

import br.com.studioequipment.repository.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPersonRepository extends MongoRepository<Person, String> {

    //List<Person> findPersonByName(String name);
}
