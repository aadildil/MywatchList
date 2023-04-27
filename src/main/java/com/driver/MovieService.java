package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie) throws MovieAlreadyExistException{
       Optional<Movie> movie1=movieRepository.findMovie(movie.getName());
       if(movie1.isPresent())
           throw new MovieAlreadyExistException(movie.getName());
       movieRepository.saveMovie(movie);

    }

    public void addDirector(Director director) {

        Optional<Director> director1=movieRepository.findDirector(director.getName());
        if(director1.isPresent())
            throw new DirectorAlreadyExistException(director.getName());
        movieRepository.saveDirector(director);
    }

    public Movie getMovieByName(String movieName) throws MovieNotFoundException{
        Optional<Movie> movie=movieRepository.findMovie(movieName);
        if(movie.isEmpty())
            throw new MovieNotFoundException(movieName);
        return movie.get();
    }


    public Director getDirectorByName(String name) {
        Optional<Director> director=movieRepository.findDirector(name);
        if(director.isEmpty())
            throw new DirectorNotFoundException(name);
        return director.get();
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        movieRepository.addMovieDirectorPair(movieName,directorName);

    }

    public List<String> getMoviesByDirectorName(String name) throws NoMoviesAddedException {
       Optional<List<String>> list=movieRepository.getMoviesByDirectorName(name);
       if(list.isEmpty())
           throw new NoMoviesAddedException(name);
       return list.get();



    }

    public Optional<List<String>> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String name) {
        movieRepository.deleteDirectorRecords(name);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();

    }
}
