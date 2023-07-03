package br.com.studioequipment.models;

import br.com.studioequipment.apis.dto.requests.PersonDTO;

public class PersonDTOBuilder {

    public static PersonDTO personDTOSuccessBuilder() {
        return PersonDTO.builder().name("jimmy").age(50L).build();
    }
}
