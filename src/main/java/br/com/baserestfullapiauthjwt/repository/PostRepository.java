package br.com.baserestfullapiauthjwt.repository;

import br.com.baserestfullapiauthjwt.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}