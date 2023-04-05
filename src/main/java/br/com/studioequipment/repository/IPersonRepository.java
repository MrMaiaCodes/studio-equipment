package br.com.studioequipment.repository;

import br.com.studioequipment.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "select d from Person d where d.name = :name")
    List<Person> findPersonByName(String name);
}
