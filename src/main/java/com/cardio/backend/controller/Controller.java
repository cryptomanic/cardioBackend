package com.cardio.backend.controller;

import com.cardio.backend.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class Controller {

    @Autowired
    CustomRepository customRepository;

    @GetMapping
    public String foo() {
        return "hello";
    }

//    @RequestMapping(path ="/{name}", method = RequestMethod.GET)
    @GetMapping("/{name}")
    public String fun(@PathVariable(value = "name") String name)
    {
        return customRepository.findByFirstName(name).toString();
    }
}
