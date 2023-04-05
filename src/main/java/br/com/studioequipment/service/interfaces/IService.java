package br.com.studioequipment.service.interfaces;

import br.com.studioequipment.exceptions.SaveMethodException;

import java.util.List;

public interface IService<T> {

    T save(T t) throws SaveMethodException;


}
