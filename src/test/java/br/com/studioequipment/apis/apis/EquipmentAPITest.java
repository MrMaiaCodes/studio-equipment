package br.com.studioequipment.apis.apis;

import br.com.studioequipment.apis.api.EquipmentAPI;
import br.com.studioequipment.apis.dto.requests.EquipmentDTO;
import br.com.studioequipment.apis.dto.responses.responses.EquipmentListResponseDTO;
import br.com.studioequipment.apis.dto.responses.responses.EquipmentResponseDTO;
import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.entities.Equipment;
import br.com.studioequipment.service.interfaces.IEquipmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EquipmentAPITest {

    @Mock
    IEquipmentService equipmentService;

    @InjectMocks
    EquipmentAPI equipmentAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddWithBodySuccess() throws SaveMethodException, PersonNotFoundException {
        when(equipmentService.save(any())).thenReturn(Equipment.builder()
                .equipmentType("gear")
                .equipmentName("microphone")
                .equipmentPrice(50L)
                .serialNumber("999")
                .idNumber("1")
                .build());

        EquipmentResponseDTO result = equipmentAPI.addWithBody(EquipmentDTO.builder()
                .equipmentType("gear")
                .equipmentName("microphone")
                .equipmentPrice(50L)
                .serialNumber("999")
                .build());
        Assertions.assertNotNull(result);
    }

    @Test
    void testAddWithBodySaveMethodExceptionError() throws SaveMethodException {
        when(equipmentService.save(any())).thenThrow(new SaveMethodException("E01",
                "Invalid Document Saved"));
        SaveMethodException thrown = Assertions.assertThrows(SaveMethodException.class, () -> {
            equipmentAPI.addWithBody(EquipmentDTO.builder()
                    .equipmentType("gear")
                    .equipmentName("microphone")
                    .equipmentPrice(50L)
                    .serialNumber("999")
                    .build());
        });
        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("Invalid Document Saved", thrown.getMessage());
    }

    @Test
    void testFindSuccess() throws EquipmentNotFoundException {
        when(equipmentService.findEquipmentByName(any()))
                .thenReturn(Equipment.builder()
                        .idNumber(1L)
                        .equipmentType("gear")
                        .equipmentName("guitar strap")
                        .equipmentPrice(500L)
                        .serialNumber("555")
                        .build());

        EquipmentResponseDTO result = equipmentAPI.find("555");
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindEquipmentNotFoundExceptionError() throws EquipmentNotFoundException {
        when(equipmentService.findEquipmentByName(any()))
                .thenThrow(new EquipmentNotFoundException("E01", "Error in finding equipment"));
        EquipmentNotFoundException thrown = Assertions.assertThrows(EquipmentNotFoundException.class,
                () -> {
                    equipmentAPI.find("555");
                });
        Assertions.assertEquals("E01", thrown.getCode());
    }

    @Test
    void testListAllEquipment() {
        when(equipmentService.listAll()).thenReturn(List.of(Equipment.builder()
                .equipmentType("gear")
                .equipmentName("mic stand")
                .equipmentPrice(50L)
                .serialNumber("999")
                .idNumber(1L)
                .build()));

        EquipmentListResponseDTO result = equipmentAPI.listAllEquipment();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getData().size());
    }

    @Test
    void testChangeWithBodySuccess() throws PersonNotFoundException, EquipmentNotFoundException {
        when(equipmentService.update(any())).thenReturn(Equipment.builder()
                .equipmentType("gear")
                .equipmentName("guitar")
                .equipmentPrice(50L)
                .serialNumber("999")
                .idNumber("1")
                .build()
        );
        EquipmentResponseDTO result = equipmentAPI.changeWithBody(EquipmentDTO.builder()
                .equipmentType("gear")
                .equipmentName("guitar")
                .equipmentPrice(50L)
                .serialNumber("999")
                .build()
        );
        Assertions.assertNotNull(result);
        Assertions.assertEquals("gear", result.getData().getEquipmentType());
        Assertions.assertEquals("guitar", result.getData().getEquipmentName());
        Assertions.assertEquals(50L, result.getData().getEquipmentPrice());
        Assertions.assertEquals("999", result.getData().getSerialNumber());
    }

    @Test
    void testChangeWithBodyEquipmentNotFoundExceptionError() throws EquipmentNotFoundException,
            PersonNotFoundException {
        when(equipmentService.update(any())).thenThrow(new EquipmentNotFoundException(
                "E01", "Equipment Not Found"));
        EquipmentNotFoundException thrown = Assertions.assertThrows(EquipmentNotFoundException.class,
                () -> {
                    equipmentAPI.changeWithBody(EquipmentDTO.builder()
                            .equipmentType("gear")
                            .equipmentName("shore")
                            .equipmentPrice(50L)
                            .serialNumber("999")
                            .build());
                });
        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("Equipment Not Found", thrown.getMessage());
    }

    @Test
    void testDeleteSuccess() throws PersonNotFoundException, EquipmentNotFoundException {
        doNothing().when(equipmentService).delete(
                Equipment.builder().equipmentType("gear").equipmentName("guitar")
                        .equipmentPrice(50L).serialNumber("999").idNumber("1").build());
        assertDoesNotThrow(() -> equipmentAPI.delete("1"));
    }

    @Test
    void testDeleteEquipmentNotFoundExceptionError() throws PersonNotFoundException,
            EquipmentNotFoundException {
        doThrow(new EquipmentNotFoundException("E01", "Equipment not found"))
                .when(equipmentService).delete(Equipment.builder()
                        .idNumber(1L)
                        .build());
        EquipmentNotFoundException thrown = Assertions.assertThrows(EquipmentNotFoundException.class,
                () -> {
                    equipmentAPI.delete(1L);
                });
        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("Equipment not found", thrown.getMessage());
    }

}

