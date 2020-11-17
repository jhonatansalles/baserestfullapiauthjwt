package br.com.baserestfullapiauthjwt.service;

import br.com.baserestfullapiauthjwt.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> findAll();

    Optional<Comment> findById(Integer id);

    void delete(Integer id);

    void save(Comment comment);
}