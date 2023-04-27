package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

   private Map<String,Movie> movies;
   private Map<String,Director> directors;
   private Map<String, List<String>> moviesdirectors;

   public MovieRepository()
   {
       movies=new HashMap<>();
       directors=new HashMap<>();
       moviesdirectors=new HashMap<>();
   }
    public Optional<Movie> findMovie(String movieName) {

       if(movies.containsKey(movieName))
       return Optional.of(movies.get(movieName));
       return Optional.empty();
    }
    public Optional<Director> findDirector(String directorName) {

        if(directors.containsKey(directorName))
            return Optional.of(directors.get(directorName));
        return Optional.empty();
    }

    public void saveDirector(Director director) {

        directors.put(director.getName(), director);
    }

    public boolean saveMovie(Movie movie) {
       movies.put(movie.getName(), movie);
       return true;
    }


    public void addMovieDirectorPair(String movieName, String directorName) {
       if(moviesdirectors.containsKey(directorName))
       {
           List<String> list=moviesdirectors.get(directorName);
           list.add(movieName);
           moviesdirectors.put(directorName,list);

       }else {
           List<String> list=new ArrayList<>();
           list.add(movieName);
           moviesdirectors.put(directorName,list);
       }

    }

    public Optional<List<String>> getMoviesByDirectorName(String name) {

       if(moviesdirectors.containsKey(name))
           return Optional.of(moviesdirectors.get(name));
       return Optional.empty();
    }

    public Optional<List<String>> findAllMovies() {
       List<String> list=new ArrayList<>();
       for(String movie:movies.keySet())
       {
           list.add(movie);
       }
       return Optional.of(list);

    }

    public void deleteDirectorRecords(String name) {
       if(moviesdirectors.containsKey(name))
       {
           List<String> list=moviesdirectors.get(name);
           for(String movie:list)
           {
               movies.remove(movie);
           }
           moviesdirectors.remove(name);

       }
       if(directors.containsKey(name))
           directors.remove(name);
    }

    public void deleteAllDirectors() {
       if(directors.size()==0)
           return;
       for(String name:directors.keySet())
       {
           deleteDirectorRecords(name);
       }
    }
}
