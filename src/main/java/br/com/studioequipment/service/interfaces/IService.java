package br.com.studioequipment.service.interfaces;

import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;

import java.util.List;

public interface IService<T> {

    T save(T t) throws SaveMethodException, PersonNotFoundException;

    void delete(T t) throws  PersonNotFoundException, EquipmentNotFoundException;

    List<T> listAll();

    T update(T t) throws PersonNotFoundException, EquipmentNotFoundException;
}
