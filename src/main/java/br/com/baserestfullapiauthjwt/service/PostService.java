package br.com.baserestfullapiauthjwt.service;

import br.com.baserestfullapiauthjwt.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> findAll();

    Optional<Post> findById(Integer id);

    void delete(Integer id);

    void save(Post post);
}