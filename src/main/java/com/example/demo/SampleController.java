package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SampleController
 */
@RestController
public class SampleController {

    @GetMapping("/")
    public String sample(){
        return "yes";
    }
}