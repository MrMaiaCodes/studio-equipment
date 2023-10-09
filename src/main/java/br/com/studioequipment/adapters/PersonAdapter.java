package br.com.studioequipment.adapters;

import br.com.studioequipment.apis.dto.requests.PersonDTO;
import br.com.studioequipment.repository.entities.Customer;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PersonAdapter {

    public static Customer convertTo(PersonDTO personDTO) {
        return Customer.builder()
                .name(personDTO.getName().toUpperCase(Locale.ROOT))
                .age(personDTO.getAge())
                .build();
    }

    public static List<Customer> convertToList(List<PersonDTO> personDTO) {
        return personDTO.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }
}
