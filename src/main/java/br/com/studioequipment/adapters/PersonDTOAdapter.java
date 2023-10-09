package br.com.studioequipment.adapters;

import br.com.studioequipment.apis.dto.requests.PersonDTO;
import br.com.studioequipment.repository.entities.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDTOAdapter {

    public static PersonDTO convertTo(Customer customer) {

        return PersonDTO.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .build();
    }

    public static List<PersonDTO> convertToList(List<Customer> customerList) {
        return customerList.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }
}
