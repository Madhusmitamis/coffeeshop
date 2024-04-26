package com.example.coffeeshop;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TuoteController {
    @Autowired
    private ToimittajaRepository toimittajaRepository;
    @Autowired
    private ValmistajaRepository valmistajaRepository;
    @Autowired
    private OsastoRepository osastoRepository;
    @Autowired
    private TuoteRepository tuoteRepository;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/admin")
    public String post(@RequestParam String nimi, @RequestParam String kuvaus, @RequestParam BigDecimal hinta,
            @RequestParam String tuotekuva) {

        return "admin";
    }
}
