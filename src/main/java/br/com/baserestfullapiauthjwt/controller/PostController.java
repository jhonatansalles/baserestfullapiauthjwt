package br.com.baserestfullapiauthjwt.controller;

import br.com.baserestfullapiauthjwt.dto.PostDTO;
import br.com.baserestfullapiauthjwt.model.Post;
import br.com.baserestfullapiauthjwt.security.services.UserPrincipal;
import br.com.baserestfullapiauthjwt.service.PostService;
import br.com.baserestfullapiauthjwt.util.ModelMapperAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class PostController extends ModelMapperAbstract<Post, PostDTO> {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> get(@PathVariable("id") Integer id) {
        Optional<Post> post = this.postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity("Erro ->Post não encontrado!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(this.convertToDto(post.get()));
    }

    @GetMapping()
    public Iterable<PostDTO> getPosts() {
        List<Post> posts = this.postService.findAll();
        return this.convertToDto(posts);
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody PostDTO postDTO) {

        UserPrincipal usuarioLogado = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postDTO.setUserId(usuarioLogado.getId());

        this.save(postDTO);

        return ResponseEntity.ok().body("Post cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.postService.delete(id);
    }

    @PutMapping()
    public ResponseEntity<String> update(@Valid @RequestBody PostDTO postDTO) {

        this.save(postDTO);

        return ResponseEntity.ok().body("Post atualizado com sucesso!");
    }

    private void save(@RequestBody @Valid PostDTO postDTO) {
        Post post = this.convertToEntity(postDTO);
        postService.save(post);
    }
}