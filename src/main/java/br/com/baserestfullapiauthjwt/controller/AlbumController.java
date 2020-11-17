package br.com.baserestfullapiauthjwt.controller;

import br.com.baserestfullapiauthjwt.dto.AlbumDTO;
import br.com.baserestfullapiauthjwt.model.Album;
import br.com.baserestfullapiauthjwt.security.services.UserPrincipal;
import br.com.baserestfullapiauthjwt.service.AlbumService;
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
@RequestMapping("/api/album")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class AlbumController extends ModelMapperAbstract<Album, AlbumDTO> {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> get(@PathVariable("id") Integer id) {
        Optional<Album> album = this.albumService.findById(id);
        if (!album.isPresent()) {
            return new ResponseEntity("Erro ->Album não encontrado!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(this.convertToDto(album.get()));
    }

    @GetMapping()
    public Iterable<AlbumDTO> getAlbums() {
        List<Album> albums = this.albumService.findAll();
        return this.convertToDto(albums);
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody AlbumDTO albumDTO) {

        UserPrincipal usuarioLogado = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        albumDTO.setUserId(usuarioLogado.getId());

        this.save(albumDTO);

        return ResponseEntity.ok().body("Album cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.albumService.delete(id);
    }

    @PutMapping()
    public ResponseEntity<String> update(@Valid @RequestBody AlbumDTO albumDTO) {

        this.save(albumDTO);

        return ResponseEntity.ok().body("Album atualizado com sucesso!");
    }

    private void save(@RequestBody @Valid AlbumDTO albumDTO) {
        Album album = this.convertToEntity(albumDTO);
        albumService.save(album);
    }
}