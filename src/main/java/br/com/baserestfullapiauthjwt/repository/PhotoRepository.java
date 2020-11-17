package br.com.baserestfullapiauthjwt.repository;

import br.com.baserestfullapiauthjwt.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

}