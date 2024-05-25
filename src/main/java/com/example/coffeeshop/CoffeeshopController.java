package com.example.coffeeshop;

import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/tuotteet")
public class CoffeeshopController {
    @Autowired
    private TuoteService tuoteService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/kahvilaitteet")
    public String kahvilaitteet(Model model) {
        model.addAttribute("kahvilaitteet",
                tuoteService.getProductForKahvilaitteet());
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
    public String kulutustuotteet(Model model) {
        model.addAttribute("kulutustuotteet",
                tuoteService.getProductForKulutustuotteet());
        return "kulutustuotteet";
    }

    @GetMapping("/vipcustomers")
    public String vipcustomer() {
        return "vipcustomers";
    }

    // @GetMapping("/tuote/{id}")
    // public String tuoteDetails(@PathVariable Long id, Model model) {
    // Tuote tuote = tuoteService.findProductById(id);
    // model.addAttribute("tuote", tuote);
    // return "tuoteDetails";
    // }

    // @GetMapping("/kuva/{id}")
    // public ResponseEntity<byte[]> getKuva(@PathVariable Long id) {
    // Tuote product = tuoteService.findProductById(id);
    // if (product != null && product.getKuva() != null) {
    // HttpHeaders headers = new HttpHeaders();
    // return new ResponseEntity<>(product.getKuva(), headers, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // }

    // @GetMapping("/vipcustomers")
    // public String vipcustomer() {
    // return "vipcustomers";
    // }

    // @GetMapping("/kahvilaitteet")
    // public String getKahvilaitteet(Model model, Pageable pageable) {
    // Page<Tuote> tuotteet = tuoteService.findByOsastoId(1L, pageable);
    // model.addAttribute("tuotteet", tuotteet);
    // return "kahvilaitteet";
    // }
    // @GetMapping("/kahvilaitteet")
    // public String getKahvilaitteet(Model model, @PageableDefault(size = 10)
    // Pageable pageable) {
    // Page<Tuote> tuotteet = tuoteService.findByOsastoId(1L, pageable);
    // model.addAttribute("tuotteet", tuotteet);
    // return "kahvilaitteet";
    // }

    // @GetMapping("/kahviLaitteet")
    // public String getKahviLaitteet(Model model, @RequestParam("id") long
    // osastoId,
    // @RequestParam(defaultValue = "0") int page) {
    // int pageSize = 6;
    // Pageable pageable = PageRequest.of(page, pageSize);
    // Page<Tuote> tuotePage = tuoteService.findByOsastoId(osastoId, pageable);

    // model.addAttribute("osastoId", osastoId);
    // model.addAttribute("totalPages", tuotePage.getTotalPages());
    // model.addAttribute("currentPage", page);
    // model.addAttribute("tuotteet", tuotePage.getContent());

    // return "kahvilaitteet"; // Updated to match the template name
    // }
}