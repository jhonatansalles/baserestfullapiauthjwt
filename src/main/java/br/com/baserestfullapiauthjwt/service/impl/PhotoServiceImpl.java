package br.com.baserestfullapiauthjwt.service.impl;

import br.com.baserestfullapiauthjwt.model.Photo;
import br.com.baserestfullapiauthjwt.repository.PhotoRepository;
import br.com.baserestfullapiauthjwt.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private static final Logger log = LoggerFactory.getLogger(PhotoServiceImpl.class);

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Photo> findAll() {
        return this.photoRepository.findAll();
    }

    @Override
    public Optional<Photo> findById(Integer id) {
        return this.photoRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        this.photoRepository.deleteById(id);
    }

    @Override
    public void save(Photo photo) {
        this.photoRepository.save(photo);
    }
}