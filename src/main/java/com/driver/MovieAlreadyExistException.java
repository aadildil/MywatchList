package com.driver;

public class MovieAlreadyExistException extends RuntimeException{
    public MovieAlreadyExistException(String name)
    {
        super("Movie "+name+" already exist");
    }
}
