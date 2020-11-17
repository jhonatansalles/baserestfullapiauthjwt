package br.com.baserestfullapiauthjwt.repository;

import br.com.baserestfullapiauthjwt.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}