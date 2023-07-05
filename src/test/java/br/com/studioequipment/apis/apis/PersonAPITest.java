package br.com.studioequipment.apis.apis;

import br.com.studioequipment.apis.api.PersonAPI;
import br.com.studioequipment.apis.dto.requests.PersonDTO;
import br.com.studioequipment.apis.dto.requests.PersonsNewEquipmentDTO;
import br.com.studioequipment.apis.dto.responses.responses.PersonListResponseDTO;
import br.com.studioequipment.apis.dto.responses.responses.PersonResponseDTO;
import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.entities.Person;
import br.com.studioequipment.service.interfaces.IPersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PersonAPITest {

    @Mock
    IPersonService personService;

    @InjectMocks
    PersonAPI personAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}

    @Test
    void testAddWithBodySuccess() throws SaveMethodException, PersonNotFoundException {
        when(personService.save(any())).thenReturn(Person.builder()
                .name("John")
                .age(2L)
                .id(15L)
                .build());

        PersonResponseDTO result = personAPI.addWithBody(PersonDTO.builder()
                .name("John")
                .age(2L)
                .build());
        Assertions.assertNotNull(result);
    }

    @Test
    void testAddWithBodySaveMethodExceptionError() throws SaveMethodException {
        when(personService.save(any())).thenThrow(new SaveMethodException("P01", "Invalid person saved"));
        SaveMethodException thrown = Assertions.assertThrows(SaveMethodException.class, () -> {
            personAPI.addWithBody(PersonDTO.builder()
                    .name("David")
                    .age(40L)
                    .build());
        });
        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Invalid person saved", thrown.getMessage());
    }

    @Test
    void testFindSuccess() throws PersonNotFoundException {
        when(personService.findPersonByName(anyString())).thenReturn(List.of(Person.builder()
                .name("Zack")
                .age(30L)
                .id(5L)
                .build())
        );

        PersonListResponseDTO result = personAPI.find("Zack");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getData().size());
    }
        @Test
        void testFindPersonNotFoundExceptionError () throws PersonNotFoundException {
        when(personService.findPersonByName(anyString()))
                .thenThrow(new PersonNotFoundException("P02", "Error in finding person"));
        PersonNotFoundException thrown = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personAPI.find("Zack");
        });
        Assertions.assertEquals("P02", thrown.getCode());
        Assertions.assertEquals("Error in finding person", thrown.getMessage());
    }

    @Test
    void testListAllPersons() {
        when(personService.listAll()).thenReturn(List.of(Person.builder()
                .id(1L)
                .build(), Person.builder().id(2L).build()));

        PersonListResponseDTO result = personAPI.listAllPersons();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.getData().size());
    }

    @Test
    void testChangeWithBodySuccess() throws PersonNotFoundException, EquipmentNotFoundException {
        when(personService.update(any())).thenReturn(Person.builder()
                .name("Tim")
                .age(29L)
                .id(1l)
                .build()
        );
        PersonResponseDTO result = personAPI.changeWithBody(PersonDTO.builder()
                .name("Tim")
                .age(29L)
                .build()
        );
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Tim", result.getData().getName());
        Assertions.assertEquals(29L, result.getData().getAge());
    }

    @Test
    void testChangeWithBodyPersonNotFoundExceptionError() throws PersonNotFoundException,
            EquipmentNotFoundException {
        when(personService.update(any())).thenThrow(new PersonNotFoundException("P01", "Person Not Found")
        );
        PersonNotFoundException thrown = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personAPI.changeWithBody(PersonDTO.builder()
                    .name("Tim")
                    .age(20L)
                    .build());
        });
        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Person Not Found", thrown.getMessage());
    }

    @Test
    void testAddEquipmentSuccess() throws PersonNotFoundException, EquipmentNotFoundException {

        doNothing().when(personService).addEquipment(anyString(), anyString());
        ResponseEntity result = personAPI.addEquipment(
                new PersonsNewEquipmentDTO("James", "1234"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testAddEquipmentPersonNotFoundExceptionError() throws PersonNotFoundException,
            EquipmentNotFoundException {
        doThrow(new PersonNotFoundException("P01", "PersonNotFound"))
                .when(personService).addEquipment(anyString(), anyString());
        PersonNotFoundException thrown = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.addEquipment("Jack", "9889");
        });
        Assertions.assertEquals("P01", ((PersonNotFoundException) thrown).getCode());
        Assertions.assertEquals("PersonNotFound", thrown.getMessage());
    }

    @Test
    void testAddEquipmentEquipmentNotFoundExceptionError() throws PersonNotFoundException, EquipmentNotFoundException {
        doThrow(new EquipmentNotFoundException("E01", "Equipment Not Found"))
                .when(personService).addEquipment(anyString(), anyString());
        EquipmentNotFoundException thrown = Assertions.assertThrows(EquipmentNotFoundException.class,
                () -> {personAPI.addEquipment(PersonsNewEquipmentDTO.builder()
                        .personName("David")
                        .equipmentSerialNumber("999")
                        .build());
        });
        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("Equipment Not Found", thrown.getMessage());
    }

    @Test
    void testDeleteSuccess() throws PersonNotFoundException, EquipmentNotFoundException {
        doNothing().when(personService).delete(Person.builder().name("James").age(23L).id(5L)
                .build());
        assertDoesNotThrow(() -> personAPI.delete(5L));
    }

    @Test
    void testDeletePersonNotFoundExceptionError() throws PersonNotFoundException, EquipmentNotFoundException {
        doThrow(new PersonNotFoundException("P01", "Person Not Found"))
                .when(personService).delete(Person.builder()
                        .id(5L)
                        .build());
        PersonNotFoundException thrown = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personAPI.delete(5L);
        });
        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Person Not Found", thrown.getMessage());
    }
}
