package com.example.traductorfinal.controllers;

import com.example.traductorfinal.services.Conector;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/traductor/")


public class traductorController {


    @PostMapping(value = "/cadena")
    public String saveAll(@RequestBody String consola) {

        System.out.println(consola);
        Conector enlace = new Conector();
        String s = enlace.traducir(consola);
        System.out.println(s);
        return s;
    }
}
