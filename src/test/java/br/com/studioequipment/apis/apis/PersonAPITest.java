package br.com.studioequipment.apis.apis;

import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.entities.Person;
import br.com.studioequipment.service.interfaces.IPersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PersonAPITest {

    @Mock
    IPersonService personService;

    @InjectMocks
    PersonAPI personAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}

    @Test
    void testAddWithBodySuccess() throws SaveMethodException {
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
        assertions.assertEquals("Invalid person saved", thrown.getMessage());
    }
}
