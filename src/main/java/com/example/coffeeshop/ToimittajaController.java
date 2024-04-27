package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String toimittaja(Model model) {
        List<Toimittaja> toimittajat = this.toimittajaRepository.findAll();
        model.addAttribute("toimittajat", toimittajat);
        return "toimittajat";
    }

    @PostMapping("/toimittajat")
    public String post(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
            @RequestParam String yhteyshenkilonEmail) {
        Toimittaja toimittaja = new Toimittaja();
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
        this.toimittajaRepository.save(toimittaja);
        return "redirect:/toimittajat";
    }
}
