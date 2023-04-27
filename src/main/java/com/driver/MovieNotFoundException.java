package com.driver;

public class MovieNotFoundException extends RuntimeException{
    public  MovieNotFoundException(String name)
    {
        super("Movie "+name+" Not found");
    }
}
