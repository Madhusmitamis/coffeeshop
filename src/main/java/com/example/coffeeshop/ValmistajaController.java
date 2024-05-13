package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class ValmistajaController {
    @Autowired
    private ValmistajaRepository valmistajaRepository;
    @Autowired
    private TuoteRepository tuoteRepository;

    @GetMapping("/valmistajat")
    public String valmistaja(Model model) {
        List<Valmistaja> valmistajat = this.valmistajaRepository.findAll();
        model.addAttribute("valmistajat", valmistajat);
        return "valmistajat";
    }

    @PostMapping("/valmistajat")
    public String create(@RequestParam String nimi, @RequestParam String url) {
        if (valmistajaRepository.existsByNimi(nimi)) {
            return "redirect:/valmistajat?error=DuplicateName";
        }
        Valmistaja valmistaja = new Valmistaja();
        valmistaja.setNimi(nimi);
        valmistaja.setUrl(url);
        this.valmistajaRepository.save(valmistaja);
        return "redirect:/valmistajat";
    }

    @GetMapping("/muokkaValmistaja/{id}")
    public String muokkaValmistaja(Model model, @PathVariable Long id) {
        Valmistaja valmistaja = valmistajaRepository.findById(id).orElse(null);
        if (valmistaja == null) {
            return "redirect:/valmistajat?error=ManufacturerNotFound";
        }
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaValmistajat";
    }

    @PostMapping("/muokkaValmistaja/{id}")
    public String paivitaValmistaja(@PathVariable Long id, @RequestParam String nimi, @RequestParam String url) {
        Valmistaja valmistaja = valmistajaRepository.findById(id).orElse(null);
        if (valmistaja == null) {
            return "redirect:/valmistajat?error=ManufacturerNotFound";
        }
        valmistaja.setNimi(nimi);
        valmistaja.setUrl(url);
        valmistajaRepository.save(valmistaja);
        return "redirect:/valmistajat";
    }

    @PostMapping("/poistaValmistaja/{id}")
    public String poistaValmistaja(@PathVariable Long id) {
        valmistajaRepository.deleteById(id);
        return "redirect:/valmistajat";
    }
}
