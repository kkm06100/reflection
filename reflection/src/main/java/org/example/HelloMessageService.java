package org.example;

public class HelloMessageService implements MessageService {
    @Override
    public String getMessage() {
        return "Hello, Dependency Injection!";
    }
}