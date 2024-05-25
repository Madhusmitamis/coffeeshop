package com.example.coffeeshop;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

@Controller
public class TuoteController {

    @Autowired
    private TuoteService tuoteService;

    @GetMapping("/admin")
    public String tuote(Model model) {
        model.addAttribute("osastot", tuoteService.findAllDepartments());
        model.addAttribute("toimittajat", tuoteService.findAllToimittajat());
        model.addAttribute("valmistajat", tuoteService.findAllValmistajat());
        model.addAttribute("tuotteet", tuoteService.findAllProducts());
        return "admin";
    }

    @PostMapping("/admin")
    public String addTuote(@RequestParam String nimi, @RequestParam String kuvaus, @RequestParam BigDecimal hinta,
            @RequestParam Long toimittajaid, @RequestParam Long valmistajaid,
            @RequestParam Long osastoid, @RequestParam("kuva") MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        tuoteService.addTuote(nimi, kuvaus, hinta, toimittajaid, valmistajaid, osastoid, bytes);

        return "redirect:/admin";

    }

    @GetMapping("/muokkaTuote/{id}")
    public String muokkaTuote(Model model, @PathVariable Long id) {
        Tuote tuote = tuoteService.findProductById(id);
        if (tuote == null) {
            return "redirect:/admin?error=ProductNotFound";
        }
        String kuvaUrl = "/kuva/" + id;
        model.addAttribute("kuvaUrl", kuvaUrl);
        model.addAttribute("tuote", tuote);
        model.addAttribute("kuvaus", tuote.getKuvaus());
        model.addAttribute("osastot", tuoteService.findAllDepartments());
        model.addAttribute("valmistajat", tuoteService.findAllValmistajat());
        model.addAttribute("toimittajat", tuoteService.findAllToimittajat());
        return "muokkaAdmin";
    }

    @PostMapping("/muokkaAdmin/{id}")
    public String paivitaTuote(@PathVariable Long id, @RequestParam String nimi,
            @RequestParam String kuvaus,
            @RequestParam BigDecimal hinta,
            @RequestParam Long toimittajaid, @RequestParam Long valmistajaid,
            @RequestParam Long osastoid) {
        tuoteService.updateTuote(id, nimi, kuvaus, hinta, toimittajaid, valmistajaid, osastoid);
        return "redirect:/admin";
    }

    // @GetMapping("/kahvilaitteet")
    // public String showProductsPage(@RequestParam(defaultValue = "1") int page,
    // Model model) {
    // int pageSize = 9; // 3 products per row * 3 rows per page
    // Page<Tuote> productPage = tuoteService.getProductsPage(page, pageSize);
    // model.addAttribute("productPage", productPage);
    // return "kahvilaitteet";
    // }

    @PostMapping("/poistaTuote/{id}")
    public String poistaTuote(@PathVariable Long id) {
        tuoteService.deleteTuote(id);
        return "redirect:/admin";
    }

}
