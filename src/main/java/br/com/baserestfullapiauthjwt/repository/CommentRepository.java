package br.com.baserestfullapiauthjwt.repository;

import br.com.baserestfullapiauthjwt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}