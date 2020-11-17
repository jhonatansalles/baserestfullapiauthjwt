package br.com.baserestfullapiauthjwt.repository;

import br.com.baserestfullapiauthjwt.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}