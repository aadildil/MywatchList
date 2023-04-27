package com.driver;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(String name)
    {
        super("Director "+" Not found");
    }
}
