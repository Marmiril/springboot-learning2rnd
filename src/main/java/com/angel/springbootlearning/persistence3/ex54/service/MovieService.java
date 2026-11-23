package com.angel.springbootlearning.persistence3.ex54.service;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

import com.angel.springbootlearning.persistence3.ex54.dto.ListMovieResponse;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieDataResponse;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieFields;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieRequest;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieResponse;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieUpdate;
import com.angel.springbootlearning.persistence3.ex54.exception.DuplicateMovie;
import com.angel.springbootlearning.persistence3.ex54.exception.InvalidMovie;
import com.angel.springbootlearning.persistence3.ex54.exception.MovieNotFound;
import com.angel.springbootlearning.persistence3.ex54.exception.MovieNotFoundByDirectorOrStock;
import com.angel.springbootlearning.persistence3.ex54.mapper.MovieMapper;
import com.angel.springbootlearning.persistence3.ex54.model.Movie;
import com.angel.springbootlearning.persistence3.ex54.repository.MovieRepository;

@Service 
public class MovieService {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    public MovieService(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ListMovieResponse getAllMovies() {
        List<MovieResponse> responses = repository.findAll()
            .stream()
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully!"
            ))
            .toList();
        return new ListMovieResponse(responses);
    }

    public ListMovieResponse searchMovies(
        String name,
        String director, 
        Integer year,
        Integer stock,
        BigDecimal value,
        Integer minYear,
        Integer maxYear,
        BigDecimal minValue, 
        BigDecimal maxValue
    ) {
        if(name != null)  { validateFields(name, "name"); }
        if(director != null) { validateFields(director, "director"); }
        if(year != null) { validateFields(year, "year"); }
        if(stock != null) { validateFields(stock, "stock"); }
        if(value != null) { validateFields(value, "value"); }

        if (minYear != null && maxYear != null && minYear.compareTo(maxYear) > 0) { throw new InvalidMovie("Invalid year range"); }
        if (minValue != null && maxValue != null && minValue.compareTo(maxValue) > 0) { throw new InvalidMovie("Invalid movie range"); }

        List<MovieResponse> responses = repository.findAll()
            .stream()
            .filter(movie -> name == null  || movie.getName().equalsIgnoreCase(name.trim()))
            .filter(movie -> director == null || movie.getDirector().equalsIgnoreCase(director.trim()))
            .filter(movie -> year == null || movie.getYear().equals(year))
            .filter(movie -> stock == null || movie.getStock().equals(stock))
            .filter(movie -> minYear == null || movie.getYear() >= minYear)
            .filter(movie -> maxYear == null || movie.getYear() <= maxYear)
            .filter(movie -> value == null || movie.getValue().compareTo(value) == 0)
            .filter(movie -> minValue == null || movie.getValue().compareTo(minValue) >= 0)
            .filter(movie -> maxValue == null || movie.getValue().compareTo(maxValue) <= 0)
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully"
            ))
            .toList();

        return new ListMovieResponse(responses);
    }

    public Movie getById(Long id) { return repository.findById(null).orElseThrow(() -> new MovieNotFound(id)); }

    public MovieDataResponse getDataResponse(Long id) { Movie movie = getById(id); return mapper.toResponse(movie); }

    public MovieResponse getMovieById(Long id) {
        Movie movie = getById(id); 
        return new MovieResponse(
            mapper.toResponse(movie),
            "Movie found successfully!"
        );
    }

    public MovieResponse getMovieByName(String name) {
        Movie movie = repository.findByNameIgnoreCase(name).orElseThrow(() -> new MovieNotFound(name));
        return new MovieResponse(
            mapper.toResponse(movie),
            "Movie found with name: " + name + " successfully!"
        );
    }

    public ListMovieResponse getMoviesByDirector(String director) {
        List<Movie> movies = repository.findByDirectorIgnoreCase(director);
        if (movies.isEmpty()) { throw new MovieNotFoundByDirectorOrStock(director); }
        List<MovieResponse> responses = movies.stream()
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully from director: " + director
            ))
            .toList();
        return new ListMovieResponse(responses);
    }

    public ListMovieResponse getByYear(Integer year) {
        List<Movie> movies = repository.findByYear(year);
        if(movies.isEmpty()) { throw new MovieNotFound(year); }
        List<MovieResponse> responses = movies.stream()
            .map(movie -> new MovieResponse(
            mapper.toResponse(movie),
            "Movie found successfully from year: " + year
            ))
            .toList();
        return new ListMovieResponse(responses);

    }

    public ListMovieResponse getByStock(Integer stock) {
        List<Movie> movies = repository.findByStock(stock);
        if (movies.isEmpty()) { throw new MovieNotFoundByDirectorOrStock(stock); }
        List<MovieResponse> responses = movies.stream()
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully!"
            ))
            .toList();
        return new ListMovieResponse(responses);        
    }

    public ListMovieResponse getByValue(BigDecimal value) {
        List<Movie> movies = repository.findByValue(value);
        if (movies.isEmpty()) { throw new MovieNotFound(value); }
        List<MovieResponse> responses = movies.stream()
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully!"
            ))
            .toList();
        return new ListMovieResponse(responses);
    }

    public ListMovieResponse getByYearBetween(Integer minYear, Integer maxYear) {
        validateFields(minYear, "year");
        validateFields(maxYear, "year");
        if (minYear >= maxYear) { throw new InvalidMovie("Invalid year range"); }

        List<Movie> movies = repository.findByYearBetween(minYear, maxYear);
       // if (movies.isEmpty()) { throw new MovieNotFound("There are no movies from " + minYear + " to " + maxYear); }
        List<MovieResponse> responses = movies.stream()
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully"
            ))
            .toList();
        return new ListMovieResponse(responses);
    }

    public ListMovieResponse getByStockBetween(Integer minStock, Integer maxStock) {
        
        validateFields(minStock, "stock");
        validateFields(maxStock, "stock");

        if (minStock > maxStock) { throw new InvalidMovie("Invalid stock range!"); }

        List<Movie> movies = repository.findByStockBetween(minStock, maxStock);

        List<MovieResponse> responses = movies.stream()
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully"                
            ))
            .toList();
        return new ListMovieResponse(responses);
        

        
    }

    public ListMovieResponse getByValueBetween(BigDecimal minValue, BigDecimal maxValue) {
        validateFields(minValue, "value");
        validateFields(maxValue, "value");

        if (minValue.signum() < 0 || maxValue.compareTo(minValue) < 0) {
            throw new InvalidMovie("Invalid value range");
        }

        List<Movie> movies = repository.findByValueBetween(minValue, maxValue);
        List<MovieResponse> responses = movies.stream()
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully!"
            ))
            .toList();
        return new ListMovieResponse(responses);
    }

    public MovieResponse createMovie (MovieRequest request) {
        validateRequest(request);
        validateUniqueMovie(null, request.name(), request.director());

        Movie movie = mapper.toModel(request);
        repository.save(movie);
        return new MovieResponse(
                mapper.toResponse(movie),
        "Movie created successfully!"
        );
    }

    public MovieResponse updateMovie (Long id, MovieUpdate request) {
        Movie movie = getById(id);
        validateRequest(request);
        //validateUniqueMovie(id, request.name(), request.director());

    }


    //////////////////////  PRIVATE METHODS //////////////////////
    
    private void validateFields(Object value, String field) { if (value == null) { throw new InvalidMovie("field" + " is required"); }

        switch (field) {
            case "name"     -> { if (!(value instanceof String name)           || name.isBlank()) { throw new InvalidMovie("Invalid movie name");}}
            case "director" -> { if(!(value instanceof String director)        || director.isBlank()) { throw new InvalidMovie("Invalid director name");}}
            case "year"     -> { if(!(value instanceof Integer year)           || year < 1895 || year > LocalDate.now().getYear()) { throw new InvalidMovie("First movie was in 1895");}}
            case "stock"    -> { if(!(value instanceof Integer stock)          || stock < 0) { throw new InvalidMovie("Stock cannot be negative!"); }}
            case "value"    -> { if(!(value instanceof BigDecimal movie_value) || movie_value.compareTo(BigDecimal.ZERO) < 0) { throw new InvalidMovie("Value cannot be negative"); }}
        }
    }

    private void validateUniqueMovie(Long currentId, String name, String director) {
        repository.findByNameIgnoreCase(name.trim())
            .ifPresent(movie -> {
                if (movie.getDirector().equalsIgnoreCase(director.trim())
                    && !movie.getId().equals(currentId)) {
                        throw new DuplicateMovie("Movie already exists");
                }
        });
    }

    private void validateRequest(MovieFields request) {
        if (request == null) { throw new InvalidMovie("Request cannot be empty!"); }
        validateFields(request.name(), "name");
        validateFields(request.director(), "director");
        validateFields(request.year(), "year");
        validateFields(request.stock(), "stock");
        validateFields(request.value(), "value");
    }

    private void validateRequest(MovieUpdate request) {
        if(request == null) { throw new InvalidMovie("Movie update data is required."); }
        validateFields(request.stock(), "stock");
        validateFields(request.value(), "value");
    }

}
