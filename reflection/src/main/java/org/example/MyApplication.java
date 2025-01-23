package org.example;

import org.example.ioc.AutoWired;

public class MyApplication {
    @AutoWired
    private MessageService messageService;

    public void run() {
        System.out.println(messageService.getMessage());
    }
}