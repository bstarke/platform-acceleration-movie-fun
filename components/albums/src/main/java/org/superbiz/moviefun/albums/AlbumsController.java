package org.superbiz.moviefun.albums;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private AlbumsRepository albumsRepository;

    public AlbumsController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @PostMapping
    @Transactional
    public void addAlbum(@RequestBody Album album) {
        albumsRepository.save(album);
    }

    @GetMapping
    public List<Album> index() {
        return albumsRepository.findAll();
    }

    @GetMapping("/{albumId}")
    public Album details(@PathVariable long albumId) {
        return albumsRepository.findOne(albumId);
    }
}
