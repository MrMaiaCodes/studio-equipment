package br.com.studioequipment.service.impl;

import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.IEquipmentRepository;
import br.com.studioequipment.repository.entities.Equipment;
import org.hibernate.annotations.ManyToAny;
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

class EquipmentServiceTet {

    @Mock
    IEquipmentRepository equipmentRepository;
    @Mock
    Logger log;
    @InjectMocks
    EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}

    @Test
    void testSaveSuccess() throws SaveMethodException {
        when(equipmentRepository.save(any()))
                .thenReturn(Equipment.builder()
                        .equipmentName("Sanheiser 800")
                        .equipmentType("microphone")
                        .equipmentPrice(999L)
                        .serialNumber("9999")
                        .idNumber(1L)
                        .build()
                );
        Equipment result = equipmentService.save(Equipment.builder()
                .equipmentName("Sanheiser 800")
                .equipmentType("microphone")
                .equipmentPrice(999L)
                .serialNumber("999")
                .idNumber(1L)
                .build());

        Assertions.assertNotNull(result);
    }

    @Test
    void testSaveSaveMethodError() throws SaveMethodException {

        SaveMethodException thrown = Assertions
                .assertThrows(SaveMethodException.class, () -> {
                    equipmentService.save(null);
                });

        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("Invalid equipment entered", thrown.getMessage());
    }

    @Test
    void testDeleteSuccess() throws EquipmentNotFoundException {
        when(equipmentRepository.findById(any())).thenReturn(Optional.of(Equipment.builder()
                .idNumber(1L)
                .equipmentType("instrument")
                .equipmentName("Shore")
                .equipmentPrice(500L)
                .build())
        );
        equipmentService.delete(Equipment.builder()
                .idNumber(1l)
                .equipmentType("instrument")
                .equipmentName("Shore")
                .equipmentPrice(500L)
                .build()
        );
    }

    @Test
    void testDeleteEquipmentNotFoundExceptionError() throws EquipmentNotFoundException {
        when(equipmentRepository.findById(any())).thenReturn(Optional.empty());
        EquipmentNotFoundException thrown = Assertions.assertThrows(EquipmentNotFoundException.class, () -> {
            equipmentService.delete(Equipment.builder()
                    .equipmentType("instrument")
                    .equipmentName("Shore microphone")
                    .equipmentPrice(500L)
                    .build()
            );
        });
        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("Equipment not found", thrown.getMessage());
    }

    @Test
    void testListAll() {
        when(equipmentRepository.findAll()).thenReturn(List.of());
    }

    @Test
    void testUpdateSuccess() throws EquipmentNotFoundException, SaveMethodException {
        when(equipmentRepository.findById(any()))
                .thenReturn(Optional.of(
                        Equipment.builder()
                                .equipmentType("equipment")
                                .equipmentName("amps")
                                .equipmentPrice(500L)
                                .build()
                ));
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(
                Equipment.builder()
                        .equipmentType("instrument")
                        .equipmentName("bass guitar")
                        .equipmentPrice(500L)
                        .build()
        );
        Equipment result = equipmentService.update(
                Equipment.builder()
                        .equipmentType("instrument")
                        .equipmentName("bass guitar")
                        .equipmentPrice(500L)
                        .build()
        );
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdateEquipmentNotFoundExceptionError() throws EquipmentNotFoundException {
        when(equipmentRepository.findById(any())).thenReturn(Optional.empty());
        EquipmentNotFoundException thrown = Assertions.assertThrows(EquipmentNotFoundException.class, () -> {
            equipmentService.update(Equipment.builder()
                    .equipmentType("gear")
                    .equipmentName("drumsticks")
                    .equipmentPrice(20L)
                    .build());
        });
        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("Equipment not found", thrown.getMessage());
    }

    @Test
    void testFindEquipmentBySerialNumberSuccess() throws EquipmentNotFoundException {
        when(equipmentRepository.findEquipmentBySerialNumber(anyString()))
                .thenReturn(Equipment.builder()
                        .equipmentType("instrument")
                        .equipmentName("drumset")
                        .equipmentPrice(1000L)
                        .serialNumber("999")
                        .idNumber(1L)
                        .build()
                );
        Equipment result = equipmentService.findEquipmentBySerialNumber("999");
        Assertions.assertNotNull(result);
    }

    @Test
    void findEquipmentBySerialNumberEquipmentNotFoundExceptionError() throws EquipmentNotFoundException {
        when(equipmentRepository.findEquipmentBySerialNumber(anyString()))
                .thenReturn(null);
        EquipmentNotFoundException thrown = Assertions.assertThrows(EquipmentNotFoundException.class,
                () -> {equipmentService.findEquipmentBySerialNumber("999");
        });
        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("Error finding equipment", thrown.getMessage());
    }


}
