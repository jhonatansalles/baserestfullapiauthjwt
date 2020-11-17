package br.com.baserestfullapiauthjwt.service.impl;

import br.com.baserestfullapiauthjwt.model.Album;
import br.com.baserestfullapiauthjwt.repository.AlbumRepository;
import br.com.baserestfullapiauthjwt.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public List<Album> findAll() {
        return this.albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Integer id) {
        return this.albumRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        this.albumRepository.deleteById(id);
    }

    @Override
    public void save(Album album) {
        this.albumRepository.save(album);
    }
}