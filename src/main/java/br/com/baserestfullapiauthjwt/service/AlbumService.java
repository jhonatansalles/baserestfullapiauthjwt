package br.com.baserestfullapiauthjwt.service;

import br.com.baserestfullapiauthjwt.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<Album> findAll();

    Optional<Album> findById(Integer id);

    void delete(Integer id);

    void save(Album album);
}