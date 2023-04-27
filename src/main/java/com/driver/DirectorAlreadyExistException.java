package com.driver;

public class DirectorAlreadyExistException extends RuntimeException{
    public DirectorAlreadyExistException(String name)
    {
        super("Director "+name+" already exist");
    }
}
