package com.example.coffeeshop;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CoffeeshopController {
    @Autowired
    private TuoteService tuoteService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // @GetMapping("/addDevice")
    // public String showAddDevice(Model model) {
    // model.addAttribute("tuote", new Tuote());
    // return "addDevice";
    // }

    // @GetMapping(path = "/files/{id}", produces = "image/png")
    // @ResponseBody
    // public byte[] getImage(@PathVariable Long id) {
    // return tuoteService.findProductById(id).orElseThrow().getKuva();
    // }

    // @PostMapping("/addDevice")
    // public String addDevice(@RequestParam String nimi,
    // @RequestParam String kuvaus,
    // @RequestParam BigDecimal hinta, @RequestParam("kuva") MultipartFile kuva)
    // throws IOException {
    // Tuote tuote = new Tuote();
    // tuote.setNimi(nimi);
    // tuote.setKuvaus(kuvaus);
    // tuote.setHinta(hinta);
    // tuote.setKuva(kuva.getBytes());
    // tuoteService.save(tuote);
    // return "redirect:/kahvilaitteet";
    // }

    @GetMapping("/kahvilaitteet")
    public String kahvilaitteet(Model model, HttpServletResponse response) {
        List<Tuote> kahvilaitteet = tuoteService.findKahvilaitteet();
        model.addAttribute("devices", kahvilaitteet);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        return "kahvilaitteet";

    }

    @GetMapping("/device/{id}")
    public String getDeviceDetails(@PathVariable Long id, Model model) {
        Tuote device = tuoteService.findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid device Id:" + id));
        model.addAttribute("devices", device);
        return "deviceDetails";
    }
    // KUVIEN PURKAMINEN
    @GetMapping("/kuva/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        Tuote product = tuoteService.getProductById(id);
        if (product != null && product.getProductImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(product.getProductImage(), headers,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
has context menu

    @GetMapping("/kulutustuotteet")
    public String product() {
        return "kulutustuotteet";
    }

    @GetMapping("/vipcustomers")
    public String vipcustomer() {
        return "vipcustomers";
    }

    @GetMapping("/laitteet")
    public String getlaittet() {
        return "laitteet";
    }

}
