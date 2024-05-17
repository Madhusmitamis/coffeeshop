package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
public class ValmistajaController {

    @Autowired
    private ValmistajaService valmistajaService;

    @GetMapping("/valmistajat")
    public String valmistaja(Model model) {
        List<Valmistaja> valmistajat = valmistajaService.findAllValmistajat();
        model.addAttribute("valmistajat", valmistajat);
        return "valmistajat";
    }

    @PostMapping("/valmistajat")
    public String create(@RequestParam String nimi, @RequestParam String url) {
        if (valmistajaService.existsByNimi(nimi)) {
            return "redirect:/valmistajat?error=DuplicateName";
        }
        // Valmistaja valmistaja = new Valmistaja();
        // valmistaja.setNimi(nimi);
        // valmistaja.setUrl(url);
        // this.valmistajaRepository.save(valmistaja);
        // return "redirect:/valmistajat";
        valmistajaService.createValmistaja(nimi, url);
        return "redirect:/valmistajat";
    }

    @GetMapping("/muokkaValmistaja/{id}")
    public String muokkaValmistaja(Model model, @PathVariable Long id) {
        Valmistaja valmistaja = valmistajaService.findValmistajaById(id);
        if (valmistaja == null) {
            return "redirect:/valmistajat?error=ManufacturerNotFound";
        }
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaValmistajat";
    }

    @PostMapping("/muokkaValmistaja/{id}")
    public String paivitaValmistaja(@PathVariable Long id, @RequestParam String nimi, @RequestParam String url) {
        valmistajaService.updateValmistaja(id, nimi, url);
        return "redirect:/valmistajat";

    }

    @PostMapping("/poistaValmistaja/{id}")
    public String poistaValmistaja(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String message;
        try {
            // Delete the supplier by ID
            valmistajaService.deleteValmistaja(id);
            message = "Valmistaja poistettiin onnistunneesti tietokannasta";
        } catch (Exception e) {
            message = "Valmistaja ei voitu poista, koska siihen liittyy tuotteita tietokannassa.";
        }
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/valmistajat";

    }

}
