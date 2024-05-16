package com.example.coffeeshop;

import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CoffeeshopController {
    @Autowired
    private TuoteService tuoteService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/kahvilaitteet")
    public String kahvilaitteet(Model model) {
        model.addAttribute("kahvilaitteet", tuoteService.getProductForKahvilaitteet());
        return "kahvilaitteet";
    }

    @GetMapping("/tuote/{id}")
    public String tuoteDetails(@PathVariable Long id, Model model) {
        Tuote tuote = tuoteService.findProductById(id);
        model.addAttribute("tuote", tuote);
        return "tuoteDetails";
    }

    // KUVIEN PURKAMINEN
    @GetMapping("/kuva/{id}")
    public ResponseEntity<byte[]> getKuva(@PathVariable Long id) {
        Tuote product = tuoteService.findProductById(id);
        if (product != null && product.getKuva() != null) {
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(product.getKuva(), headers,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/kulutustuotteet")
    public String product() {
        return "kulutustuotteet";
    }

    @GetMapping("/vipcustomers")
    public String vipcustomer() {
        return "vipcustomers";
    }

}
