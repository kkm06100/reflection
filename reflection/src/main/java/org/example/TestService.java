package org.example;

import org.example.ioc.AutoWired;

@Component
public class TestService {
    @AutoWired
    private CL classLoad;


}
