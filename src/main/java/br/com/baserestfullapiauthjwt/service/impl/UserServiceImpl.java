package br.com.baserestfullapiauthjwt.service.impl;

import br.com.baserestfullapiauthjwt.model.User;
import br.com.baserestfullapiauthjwt.repository.UserRepository;
import br.com.baserestfullapiauthjwt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        this.userRepository.save(user);
    }
}