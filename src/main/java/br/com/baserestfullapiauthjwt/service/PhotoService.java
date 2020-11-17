package br.com.baserestfullapiauthjwt.service;

import br.com.baserestfullapiauthjwt.model.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoService {

    List<Photo> findAll();

    Optional<Photo> findById(Integer id);

    void delete(Integer id);

    void save(Photo photo);
}