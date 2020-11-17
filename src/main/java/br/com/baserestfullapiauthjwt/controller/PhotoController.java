package br.com.baserestfullapiauthjwt.controller;

import br.com.baserestfullapiauthjwt.dto.PhotoDTO;
import br.com.baserestfullapiauthjwt.model.Album;
import br.com.baserestfullapiauthjwt.model.Photo;
import br.com.baserestfullapiauthjwt.security.services.UserPrincipal;
import br.com.baserestfullapiauthjwt.service.AlbumService;
import br.com.baserestfullapiauthjwt.service.PhotoService;
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
@RequestMapping("/api/photos")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class PhotoController extends ModelMapperAbstract<Photo, PhotoDTO> {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private AlbumService albumService;

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDTO> get(@PathVariable("id") Integer id) {
        Optional<Photo> photo = this.photoService.findById(id);
        if (!photo.isPresent()) {
            return new ResponseEntity("Erro ->Photo não encontrado!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(this.convertToDto(photo.get()));
    }

    @GetMapping()
    public Iterable<PhotoDTO> getPhotos() {
        List<Photo> photos = this.photoService.findAll();
        return this.convertToDto(photos);
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody PhotoDTO photoDTO) {

        this.save(photoDTO);

        return ResponseEntity.ok().body("Photo cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.photoService.delete(id);
    }

    @PutMapping()
    public ResponseEntity<String> update(@Valid @RequestBody PhotoDTO photoDTO) {

        return this.save(photoDTO);
    }

    private ResponseEntity<String> save(@RequestBody @Valid PhotoDTO photoDTO) {

        UserPrincipal usuarioLogado = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Album> albumOp = this.albumService.findById(photoDTO.getAlbumId());
        if (!albumOp.isPresent()) {
            return new ResponseEntity("Erro ->Album não encontrado!", HttpStatus.BAD_REQUEST);
        }

        Album album = albumOp.get();
        if (!album.getUser().getId().equals(usuarioLogado.getId())) {
            return new ResponseEntity("Erro ->Usuário não é proprietario do Album informado!", HttpStatus.BAD_REQUEST);

        }

        Photo photo = this.convertToEntity(photoDTO);
        photoService.save(photo);

        return ResponseEntity.ok().body("Photo atualizado com sucesso!");
    }
}