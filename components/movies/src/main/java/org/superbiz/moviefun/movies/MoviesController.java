package org.superbiz.moviefun.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MoviesRepository moviesRepository;

    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        moviesRepository.save(movie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovieId(@PathVariable Long movieId) {
        moviesRepository.delete(movieId);
    }

    @GetMapping("/count")
    public long count(
        @RequestParam(name = "field", required = false) String field,
        @RequestParam(name = "key", required = false) String key
    ) {
        if (field != null && key != null) {
            return moviesRepository.count();
        } else {
            return moviesRepository.count();
        }
    }

    @GetMapping
    public List<Movie> find(
        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
        @RequestParam(name = "field", required = false) String field,
        @RequestParam(name = "key", required = false) String key
    ) {
        logger.debug("Page: {};  PageSize: {};  Field: {};  Key: {}", page, pageSize, field, key);
        if (field != null && key != null) {
            Sort sort = new Sort(Sort.Direction.ASC, field);
            return moviesRepository.findAll(new PageRequest(page, pageSize, sort)).getContent();
        } else if (page != null && pageSize != null) {
            return moviesRepository.findAll(new PageRequest(page ,pageSize)).getContent();
        } else {
            return moviesRepository.findAll();
        }
    }
}
