package com.example.demo.customExceptions;

public class CustomInternalServerErrorException extends RuntimeException{

    public CustomInternalServerErrorException(){
        super("Something went wrong !!");
    }

    public CustomInternalServerErrorException(String message){
        super(message);
    }
}
