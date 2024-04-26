package com.example.coffeeshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OsastoController {
    @Autowired
    private OsastoRepository osastoRepository;
    @Autowired
    private TuoteRepository tuoteRepository;

    @PostMapping("/osasto")
    public String create(@RequestParam String nimi, @RequestParam long osastoId) {
        // osastoRepository.save(new osasto(nimi));
        return "redirect:/";

    }
}
