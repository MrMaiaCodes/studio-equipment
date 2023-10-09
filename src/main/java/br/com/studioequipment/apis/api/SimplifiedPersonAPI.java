package br.com.studioequipment.apis.api;

import br.com.studioequipment.apis.dto.requests.PersonDTO;
import br.com.studioequipment.repository.entities.Customer;
import br.com.studioequipment.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("V2/person-simplified")
public class SimplifiedPersonAPI {

    @Autowired
    IPersonService personService;

    @PostMapping("/save")
    public Customer personSave(@RequestBody PersonDTO personDTO) {

        return personService.saveSimplified(personDTO);

    };
}
