package br.com.studioequipment.repository;

import br.com.studioequipment.repository.entities.Microphone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMicrophoneRepository extends MongoRepository<Microphone, String> {

    Microphone findMicrophoneByName(String name);
}
