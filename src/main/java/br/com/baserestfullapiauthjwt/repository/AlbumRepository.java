package br.com.baserestfullapiauthjwt.repository;

import br.com.baserestfullapiauthjwt.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

}