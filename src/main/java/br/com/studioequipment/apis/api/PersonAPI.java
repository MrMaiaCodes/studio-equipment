package br.com.studioequipment.apis.api;

import br.com.studioequipment.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("V1/person")
public class PersonAPI {

    @Autowired
    private IPersonService personService;

    @PostMapping("/new")
    public PersonResponseDTO.builder()
            .data(
                    PersonDTOAdapter.convertTo(
                            personService.save(PersonAdapter.convertTo(personDTO))
    )
    ).build();
}
