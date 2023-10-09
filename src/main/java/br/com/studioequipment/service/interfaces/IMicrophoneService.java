package br.com.studioequipment.service.interfaces;

import br.com.studioequipment.exceptions.MicrophoneNotFoundException;
import br.com.studioequipment.repository.entities.Microphone;

import java.util.List;

public interface IMicrophoneService extends IService<Microphone> {

    List<Microphone> findMicrophoneByModel(String model) throws MicrophoneNotFoundException;
}
