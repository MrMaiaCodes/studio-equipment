package br.com.studioequipment.apis.dto.responses.responses;

import br.com.studioequipment.apis.dto.requests.PersonDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonListResponseDTO {

    public List<PersonDTO> data;
}
