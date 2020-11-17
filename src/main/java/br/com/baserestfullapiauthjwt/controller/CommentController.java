package br.com.baserestfullapiauthjwt.controller;

import br.com.baserestfullapiauthjwt.dto.CommentDTO;
import br.com.baserestfullapiauthjwt.model.Comment;
import br.com.baserestfullapiauthjwt.security.services.UserPrincipal;
import br.com.baserestfullapiauthjwt.service.CommentService;
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
@RequestMapping("/api/comment")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class CommentController extends ModelMapperAbstract<Comment, CommentDTO> {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> get(@PathVariable("id") Integer id) {
        Optional<Comment> comment = this.commentService.findById(id);
        if (!comment.isPresent()) {
            return new ResponseEntity("Erro ->Comentario não encontrado!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(this.convertToDto(comment.get()));
    }

    @GetMapping()
    public Iterable<CommentDTO> getComments() {
        List<Comment> comments = this.commentService.findAll();
        return this.convertToDto(comments);
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody CommentDTO commentDTO) {

        UserPrincipal usuarioLogado = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentDTO.setUserId(usuarioLogado.getId());

        this.save(commentDTO);

        return ResponseEntity.ok().body("Comentario cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.commentService.delete(id);
    }

    @PutMapping()
    public ResponseEntity<String> update(@Valid @RequestBody CommentDTO commentDTO) {

        this.save(commentDTO);

        return ResponseEntity.ok().body("Comentario atualizado com sucesso!");
    }

    private void save(@RequestBody @Valid CommentDTO commentDTO) {
        Comment comment = this.convertToEntity(commentDTO);
        commentService.save(comment);
    }
}