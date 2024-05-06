package com.example.coffeeshop;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OsastoController {
    @Autowired
    private OsastoRepository osastoRepository;
    @Autowired
    private TuoteRepository tuoteRepository;

    @GetMapping("/osastot")
    public String osasto(Model model) {
        model.addAttribute("osastot", osastoRepository.findAll());
        // model.addAttribute("tuotteet", tuoteRepository.findAll());
        return "osastot";
    }

    @PostMapping("/osastot")
    public String create(@RequestParam String nimi, @RequestParam Long osastoIdp) {
        if (osastoRepository.existsByNimi(nimi)) {
            return "redirect:/osastot?error=DuplicateName";
        }
        Osasto osasto = new Osasto();
        osasto.setNimi(nimi);
        osasto.setOsastoIdp(osastoIdp);
        osastoRepository.save(osasto);
        return "redirect:/osastot";
    }

    // @GetMapping("/osastot/{id}")
    // public String getOne(Model model, @PathVariable Long id) {
    // model.addAttribute("osastot", osastoRepository.getOne(id));
    // return "osastot";
    // }

    // @PostMapping("/osastot/{id}/admin")
    // public String addTuote(@PathVariable Long id, @RequestParam String nimi,
    // @RequestParam BigDecimal hinta,
    // @RequestParam String kuvaus, @RequestParam String tuotekuva) {
    // Osasto o = osastoRepository.getOne(id);
    // Tuote t = new Tuote(nimi, hinta, kuvaus, tuotekuva, o);
    // tuoteRepository.save(t);
    // return "redirect:/osastot/" + id;
    // }

}
