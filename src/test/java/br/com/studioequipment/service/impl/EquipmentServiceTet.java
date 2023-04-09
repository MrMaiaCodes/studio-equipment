package br.com.studioequipment.service.impl;

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

import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
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
}
