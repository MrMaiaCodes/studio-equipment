package br.com.studioequipment.service.impl;

import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.IPersonRepository;
import br.com.studioequipment.repository.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PersonServiceTest {

    @Mock
    IPersonRepository personRepository;

    @Mock
    Logger log;

    @InjectMocks
    PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSuccess() throws SaveMethodException {
        when(personRepository.save(any())).thenReturn(Person.builder().id(25L).build());
        Person result = personService.save(Person.builder().name("Steven").age(30L).build());
        Assertions.assertNotNull(result);
    }

    @Test
    void testSaveNullNameError() throws SaveMethodException {
        SaveMethodException thrown = Assertions.assertThrows(SaveMethodException.class, () -> {
            personService.save(Person.builder().name("").age(20L).build());

        });

        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Invalid Person saved", thrown.getMessage());
    }

    @Test
    void testSaveAgeZeroError() throws SaveMethodException {
        SaveMethodException thrown = Assertions.assertThrows(SaveMethodException.class, () -> {
            personService.save(Person.builder().name("Zack").age(0L).build());
        });

        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Invalid Person saved", thrown.getMessage());
    }

    @Test
    void findPersonByNameSuccess() throws PersonNotFoundException {
        when(personRepository.findPersonByName(anyString()))
                .thenReturn(List.of(Person.builder()
                        .id(11L)
                        .name("Joaquin")
                        .age(50L)
                        .build())
                );
        List<Person> personFound = personService.findPersonByName("Joaquin");
        Assertions.assertNotNull(personFound);
    }

    @Test
    void findPersonByNamePersonNotFoundExceptionError() throws PersonNotFoundException {
        when(personRepository.findPersonByName(anyString()))
                .thenReturn(null);
        PersonNotFoundException thrown = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.findPersonByName("Mark");
        });
        Assertions.assertEquals("P02", thrown.getCode());
        Assertions.assertEquals("Error in finding Mark.", thrown.getMessage());
    }

    @Test
    void testListAllSuccess() {
        when(personRepository.findAll()).thenReturn(List.of());
        List<Person> personList = personService.listAll();
        Assertions.assertNotNull(personList);
    }

    @Test
    void testDeleteSuccess() throws PersonNotFoundException {
        when(personRepository.findById(any())).thenReturn(Optional.of(Person.builder()
                .id(03L)
                .name("Jamie")
                .age(23L)
                .build())
        );
        personService.delete(Person.builder()
                .id(03L)
                .name("Jamie")
                .age(23L)
                .build()
        );
    }

    @Test
    void testDeletePersonNotFoundExceptionError() throws PersonNotFoundException {
        when(personRepository.findById(any())).thenReturn(Optional.empty());
        PersonNotFoundException thrown = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.delete(Person.builder()
                    .name("Bartholomew")
                    .age(48L)
                    .build());
        });
        Assertions.assertEquals("P01", thrown.getCode());
        Assertions.assertEquals("Person Not Found", thrown.getMessage());
    }

}
