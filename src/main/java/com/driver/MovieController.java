package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie)
    {
        try {
            movieService.addMovie(movie);
            return new ResponseEntity<>("Movie Successfully Added",HttpStatus.CREATED);
        }
        catch (MovieAlreadyExistException exception)
        {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }


    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director)
    {
        try {
            movieService.addDirector(director);
            return new ResponseEntity<>("added new director Successfully ",HttpStatus.valueOf(201));
        }
        catch (DirectorAlreadyExistException exception)
        {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }


    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movieName,@RequestParam String directorName )
    {
       try
       {
           Movie movie= movieService.getMovieByName(movieName);
          Director director= movieService.getDirectorByName(directorName);

           movieService.addMovieDirectorPair(movieName,directorName);
           return new ResponseEntity<>("Successfully updated directors and movies list",HttpStatus.CREATED);

       }
       catch (MovieNotFoundException exception)
       {
           return new ResponseEntity<>(exception.getMessage(),HttpStatus.valueOf(404));

       }catch (DirectorNotFoundException exception)
       {
           return new ResponseEntity<>(exception.getMessage(),HttpStatus.valueOf(404));

       }



    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name) throws MovieNotFoundException,DirectorNotFoundException
    {
        try {
           Movie movie= movieService.getMovieByName(name);
           return new ResponseEntity(movie, HttpStatus.OK);
        }catch (MovieNotFoundException exception)
        {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.valueOf(404));

        }

    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name)
    {
        try {
            Director director= movieService.getDirectorByName(name);
            return new ResponseEntity(director, HttpStatus.OK);
        }catch (DirectorNotFoundException exception)
        {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.valueOf(404));

        }
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director)
    {
        try
        {
            movieService.getDirectorByName(director);
            return new ResponseEntity<>(movieService.getMoviesByDirectorName(director),HttpStatus.OK);
        }
        catch(DirectorNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(404));
        }
        catch (NoMoviesAddedException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }

    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies()
    {
        Optional<List<String>> list=movieService.findAllMovies();
        if(list.isEmpty())
            return new ResponseEntity<>("No movies are added",HttpStatus.OK);
        return new ResponseEntity(list.get(),HttpStatus.OK);

    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name)
    {
        try
        {
            Director director= movieService.getDirectorByName(name);
            movieService.deleteDirectorByName(name);
            return new ResponseEntity("Successfully deleted all records of director "+name,HttpStatus.OK);
        }catch (DirectorNotFoundException exception)
        {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.valueOf(404));

        }
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors()
    {
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("Successfully deleted all director records",HttpStatus.OK);

    }
}
