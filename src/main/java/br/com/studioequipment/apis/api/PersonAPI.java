package br.com.studioequipment.apis.api;

import br.com.studioequipment.adapters.PersonAdapter;
import br.com.studioequipment.adapters.PersonDTOAdapter;
import br.com.studioequipment.apis.dto.requests.PersonDTO;
import br.com.studioequipment.apis.dto.requests.PersonsNewEquipmentDTO;
import br.com.studioequipment.apis.dto.responses.responses.DeleteResponseDTO;
import br.com.studioequipment.apis.dto.responses.responses.PersonListResponseDTO;
import br.com.studioequipment.apis.dto.responses.responses.PersonResponseDTO;
import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.entities.Person;
import br.com.studioequipment.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("V1/person")
public class PersonAPI {

    @Autowired
    private IPersonService personService;

    @PostMapping("/new")
    public PersonResponseDTO addWithBody(@RequestBody PersonDTO personDTO) throws SaveMethodException {
        return PersonResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertTo(
                                personService.save(PersonAdapter.convertTo(personDTO))
                        )
                ).build();
    }

    @GetMapping("/find/{person}")
    public PersonListResponseDTO find(@PathVariable("person") String personName) throws PersonNotFoundException {

        return PersonListResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertToList(
                                personService.findPersonByName(personName)
                        )
                ).build();
    }

    @GetMapping("/list")
    public PersonListResponseDTO listAllPersons() {
        return PersonListResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertToList(
                                personService.listAll()
                        )
                ).build();
    }

    @PutMapping("/change/{name}")
    public PersonResponseDTO changeWithBody(@RequestBody PersonDTO personDTO) throws PersonNotFoundException,
            EquipmentNotFoundException {
        return PersonResponseDTO.builder()
                .data(
                        PersonDTOAdapter.convertTo(
                                personService.update(
                                        PersonAdapter.convertTo(personDTO)
                                )
                        )
                ).build();
    }

    @PostMapping
    public ResponseEntity addEquipment(@RequestBody PersonsNewEquipmentDTO newEquipmentDTO)
        throws EquipmentNotFoundException, PersonNotFoundException {

        personService.addEquipment(newEquipmentDTO.getPersonName(), newEquipmentDTO.getEquipmentSerialNumber());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("personId") Long personId)
            throws PersonNotFoundException, EquipmentNotFoundException {
        personService.delete(Person.builder().id(personId).build());

        return ResponseEntity.ok(DeleteResponseDTO.builder()
                .deleteSuccessMessage("Person successfully deleted")
                .build());
    }

}
