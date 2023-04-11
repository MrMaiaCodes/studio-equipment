package br.com.studioequipment.adapters;

import br.com.studioequipment.apis.dto.requests.PersonDTO;
import br.com.studioequipment.repository.entities.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDTOAdapter {

    public static PersonDTO convertTo(Person person) {

        return PersonDTO.builder()
                .name(person.getName())
                .age(person.getAge())
                .build();
    }

    public static List<PersonDTO> convertToList(List<Person> personList) {
        return personList.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }
}
