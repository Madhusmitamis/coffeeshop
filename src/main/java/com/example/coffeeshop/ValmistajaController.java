package com.example.coffeeshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ValmistajaController {
    @Autowired
    private ValmistajaRepository valmistajaRepository;
    @Autowired
    private TuoteRepository tuoteRepository;

    @GetMapping("/valmistajat")
    public String valmistaja() {
        return "valmistajat";
    }

    @PostMapping("/valmistajat")
    public String post(@RequestParam String nimi, @RequestParam String url) {

        return "valmistajat";
    }
}
