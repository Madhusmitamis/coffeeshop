package com.example.coffeeshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToimittajaController {
    @Autowired
    private ToimittajaRepository toimittajaRepository;
    @Autowired
    private TuoteRepository tuoteRepository;

    @GetMapping("/toimittajat")
    public String toimittaja() {
        return "toimittajat";
    }

    @PostMapping("/toimittajat")
    public String post(@RequestParam String nimi, @RequestParam String yhteyshenkilö,
            @RequestParam String yhteyshenkilönEmail) {

        return "toimittajat";
    }
}
