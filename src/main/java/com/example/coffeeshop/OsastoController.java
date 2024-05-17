package com.example.coffeeshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OsastoController {

    @Autowired
    private OsastoService osastoService;

    @GetMapping("/osastot")
    public String osasto(Model model) {
        model.addAttribute("osastot", osastoService.findAllOsastot());
        // model.addAttribute("tuotteet", tuoteRepository.findAll());
        return "osastot";
    }

    @PostMapping("/osastot")
    public String create(@RequestParam String nimi, @RequestParam Long osastoIdp) {
        if (osastoService.existsByNimi(nimi)) {
            return "redirect:/osastot?error=DuplicateName";
        }
        Osasto osasto = new Osasto();
        osasto.setNimi(nimi);
        osasto.setOsastoIdp(osastoIdp);
        osastoService.save(osasto);
        return "redirect:/osastot";
    }

    @GetMapping("/muokkaOsasto/{id}")
    public String muokkaOsasto(Model model, @PathVariable Long id) {
        Osasto osasto = osastoService.findOsastoById(id);
        if (osasto == null) {
            return "redirect:/osastot?error=DepartmentNotFound";
        }
        model.addAttribute("osasto", osasto);
        return "muokkaOsasto";
    }

    @PostMapping("/muokkaOsasto/{id}")
    public String paivitaOsasto(@PathVariable Long id, @RequestParam String nimi, @RequestParam Long osastoIdp) {
        Osasto osasto = osastoService.findOsastoById(id);
        if (osasto == null) {
            return "redirect:/osastot?error=DepartmentNotFound";
        }
        osastoService.updateOsasto(id, nimi, osastoIdp);
        return "redirect:/osastot";
    }

    @PostMapping("/poistaOsasto/{id}")
    public String poistaOsasto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String message;
        try {
            // Delete the supplier by ID
            osastoService.deleteOsasto(id);
            message = "Osasto poistettiin onnistunneesti tietokannasta";
        } catch (Exception e) {
            message = "Osasto ei voitu poista, koska siihen liittyy tuotteita tietokannassa.";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/osastot";
    }

}
