package com.driver;

public class NoMoviesAddedException extends RuntimeException{
    public NoMoviesAddedException(String name)
    {
        super("No movies are added from director "+name);
    }
}
