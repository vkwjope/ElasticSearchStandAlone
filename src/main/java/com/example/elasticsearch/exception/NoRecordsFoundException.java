package com.example.elasticsearch.exception;

public class NoRecordsFoundException extends RuntimeException{
    public NoRecordsFoundException(String statusCode, String status, String message) {
        //super(message);
    }
}
