package br.com.studioequipment.service.impl;

import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.MicrophoneNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.entities.Microphone;
import br.com.studioequipment.service.interfaces.IMicrophoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MicrophoneService implements IMicrophoneService {
    @Override
    public List<Microphone> findMicrophoneByModel(String model) throws MicrophoneNotFoundException {
        return null;
    }

    @Override
    public Microphone save(Microphone microphone) throws SaveMethodException, PersonNotFoundException {
        return null;
    }

    @Override
    public void delete(Microphone microphone) throws PersonNotFoundException, EquipmentNotFoundException {

    }

    @Override
    public List<Microphone> listAll() {
        return null;
    }

    @Override
    public Microphone update(Microphone microphone) throws PersonNotFoundException, EquipmentNotFoundException {
        return null;
    }
}
