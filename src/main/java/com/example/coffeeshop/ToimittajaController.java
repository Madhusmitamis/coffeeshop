package com.example.coffeeshop;

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
    private ToimittajaService toimittajaService;

    // @GetMapping("/toimittajat")
    // public String toimittaja(Model model) {
    // List<Toimittaja> toimittajat = this.toimittajaRepository.findAll();
    // model.addAttribute("toimittajat", toimittajat);
    @GetMapping("/toimittajat")
    public String toimittaja(Model model) {
        model.addAttribute("toimittajat", toimittajaService.findAllToimittajat());
        return "toimittajat";

    }

    @PostMapping("/toimittajat")
    public String lisaaToimittaja(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
            @RequestParam String yhteyshenkilonEmail) {
        toimittajaService.addToimittaja(nimi, yhteyshenkilo, yhteyshenkilonEmail);
        return "redirect:/toimittajat?error=DuplicateName";
    }

    // Toimittaja toimittaja = new
    // Toimittaja();toimittaja.setNimi(nimi);toimittaja.setYhteyshenkilo(yhteyshenkilo);toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);this.toimittajaRepository.save(toimittaja);return"redirect:/toimittajat";
    // }

    @GetMapping("/muokkaToimittaja/{id}")
    public String muokkaToimittaja(Model model, @PathVariable Long id) {
        Toimittaja toimittaja = toimittajaService.findToimittajaById(id);
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
        toimittajaService.updateToimittaja(id, nimi, yhteyshenkilo, yhteyshenkilonEmail);
        return "redirect:/toimittajat";
        // Toimittaja toimittaja =
        // toimittajaService.findToimittajaById(id).orElse(null);
        // if (toimittaja == null) {
        // // Handle the case where the supplier is not found
        // // For example, you can redirect to an error page
        // return "redirect:/toimittajat?error=SupplierNotFound";
        // }
        // toimittaja.setNimi(nimi);
        // toimittaja.setYhteyshenkilo(yhteyshenkilo);
        // toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
        // toimittajaRepository.save(toimittaja);
        // return "redirect:/toimittajat";
    }

    @PostMapping("/poistaToimittaja/{id}")
    public String poistaToimittaja(@PathVariable Long id) {
        // Delete the supplier by ID
        toimittajaService.deleteToimittaja(id);
        return "redirect:/toimittajat";
    }
}
