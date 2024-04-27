package com.example.coffeeshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OsastoController {
    @Autowired
    private OsastoRepository osastoRepository;
    @Autowired
    private TuoteRepository tuoteRepository;

    @GetMapping("/osastot")
    public String osasto() {
        return "osastot";
    }

    @PostMapping("/osastot")
    public String create(@RequestParam String nimi, @RequestParam Long osastoIdp) {
        // osastoRepository.save(new osasto(nimi));
        return "redirect:/osastot";

    }
}
