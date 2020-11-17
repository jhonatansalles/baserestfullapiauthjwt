package br.com.baserestfullapiauthjwt.repository;

import br.com.baserestfullapiauthjwt.model.Geo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends JpaRepository<Geo, Integer> {

}