package br.com.movieflix.sevirce;

import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;


    public Movie savedMovie(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStremings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findMovieId(Long id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> update(Long id, Movie updateMovie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {

            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> stremings = this.findStremings(updateMovie.getStreamings());

            Movie movie = optionalMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());
            movie.setRealeaseDate(updateMovie.getRealeaseDate());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(stremings);

            movieRepository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    public List<Movie> findByCategory(Long categoryId) {
        return movieRepository.findMoviesByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        for (Category category : categories) {
            categoryService.findById(category.getId()).ifPresent(categoriesFound::add);
        }
        return categoriesFound;
    }

    private List<Streaming> findStremings(List<Streaming> streamings) {
        List<Streaming> stremingsFound = new ArrayList<>();
        for (Streaming streming : streamings) {
            streamingService.findById(streming.getId()).ifPresent(stremingsFound::add);
        }
        return stremingsFound;
    }


}