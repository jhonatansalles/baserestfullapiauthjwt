package br.com.baserestfullapiauthjwt.service;

import br.com.baserestfullapiauthjwt.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Integer id);

    void delete(Integer id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void save(User user);
}