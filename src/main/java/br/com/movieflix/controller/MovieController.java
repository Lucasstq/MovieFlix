package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.sevirce.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("movieflix/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> list = movieService.getAllMovies().stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@Valid @RequestBody MovieRequest movieRequest) {
        Movie savedMovie = movieService.savedMovie(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        return movieService.findMovieId(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest) {
        return movieService.update(id, MovieMapper.toMovie(movieRequest))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category) {
        return ResponseEntity.ok(movieService.findByCategory(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Optional<Movie> optMovie = movieService.findMovieId(id);
        if (optMovie.isPresent()) {
            movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
