package br.com.studioequipment.apis.dto.responses.responses;

import br.com.studioequipment.apis.dto.requests.PersonDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponseDTO {

    public PersonDTO data;
}
