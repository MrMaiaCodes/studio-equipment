package br.com.studioequipment.service.impl;

import br.com.studioequipment.repository.IEquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.logging.Logger;

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
}
