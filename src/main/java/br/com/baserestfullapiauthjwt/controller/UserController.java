package br.com.baserestfullapiauthjwt.controller;

import br.com.baserestfullapiauthjwt.dto.UserDTO;
import br.com.baserestfullapiauthjwt.model.User;
import br.com.baserestfullapiauthjwt.service.UserService;
import br.com.baserestfullapiauthjwt.util.ModelMapperAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController extends ModelMapperAbstract<User, UserDTO> {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable("id") Integer id) {
        Optional<User> user = this.userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity("Erro ->Usuário não encontrado!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(this.convertToDto(user.get()));
    }

    @GetMapping()
    public Iterable<UserDTO> getUsers() {
        List<User> users = this.userService.findAll();
        return this.convertToDto(users);
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody UserDTO userDTO) {

        if(userService.existsByUsername(userDTO.getUsername())) {
            return new ResponseEntity("Erro -> O nome de usuário já está em uso!", HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(userDTO.getEmail())) {
            return new ResponseEntity("Erro -> O e-mail já está sendo usado!", HttpStatus.BAD_REQUEST);
        }

        this.save(userDTO);

        return ResponseEntity.ok().body("Usuário registrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.userService.delete(id);
    }

    @PutMapping()
    public ResponseEntity<String> update(@Valid @RequestBody UserDTO userDTO) {

        this.save(userDTO);

        return ResponseEntity.ok().body("Usuário atualizado com sucesso!");
    }

    private void save(@RequestBody @Valid UserDTO userDTO) {
        User user = this.convertToEntity(userDTO);
        userService.save(user);
    }
}