package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String lisaaToimittaja(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
            @RequestParam String yhteyshenkilonEmail) {
        if (toimittajaRepository.existsByNimi(nimi)) {
            // Handle duplicate supplier name error
            // You can redirect to the supplier list page with an error message
            return "redirect:/toimittajat?error=DuplicateName";
        }
        Toimittaja toimittaja = new Toimittaja();
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
        this.toimittajaRepository.save(toimittaja);
        return "redirect:/toimittajat";
    }

    @GetMapping("/muokkaToimittaja/{id}")
    public String muokkaToimittaja(Model model, @PathVariable Long id) {
        Toimittaja toimittaja = toimittajaRepository.findById(id).orElse(null);
        if (toimittaja == null) {
            return "redirect:/toimittajat?error=SupplierNotFound";
        }
        model.addAttribute("toimittaja", toimittaja);
        return "muokkaToimittajat";
    }

    @PostMapping("/muokkaToimittaja/{id}")
    public String paivitaToimittaja(@PathVariable Long id, @RequestParam String nimi,
            @RequestParam String yhteyshenkilo,
            @RequestParam String yhteyshenkilonEmail) {
        Toimittaja toimittaja = toimittajaRepository.findById(id).orElse(null);
        if (toimittaja == null) {
            // Handle the case where the supplier is not found
            // For example, you can redirect to an error page
            return "redirect:/toimittajat?error=SupplierNotFound";
        }
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
        toimittajaRepository.save(toimittaja);
        return "redirect:/toimittajat";
    }

    @PostMapping("/poistaToimittaja/{id}")
    public String poistaToimittaja(@PathVariable Long id) {
        // Delete the supplier by ID
        toimittajaRepository.deleteById(id);
        return "redirect:/toimittajat";
    }
}
