package br.com.baserestfullapiauthjwt.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ModelMapperAbstract<E, D> {

    @Autowired
    private ModelMapper modelMapper;

    public E convertToEntity(D dto) {
        if (dto == null)
            return null;

        E entity = modelMapper.map(dto, (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
        return entity;
    }

    public List<E> convertToEntity(List<D> dtos) {
        if (dtos == null)
            return null;

        return dtos.stream().map(i -> convertToEntity(i)).collect(Collectors.toList());
    }

    public D convertToDto(E entity) {
        if (entity == null)
            return null;

        D dto = modelMapper.map(entity, (Class<D>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
        return dto;
    }

    public List<D> convertToDto(List<E> entitys) {
        if (entitys == null)
            return null;

        return entitys.stream().map(i -> convertToDto(i)).collect(Collectors.toList());
    }
}
