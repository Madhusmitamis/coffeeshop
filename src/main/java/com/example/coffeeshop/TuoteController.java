package com.example.coffeeshop;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/searchKahvilaitteet")
    public String searchKahvilaitteet(@RequestParam("keyword") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            Model model) {

        Sort.Direction direction = Sort.Direction.fromString(sortDir);
        Sort sort = Sort.by(direction, "hinta");
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Long> kahvilaitteetOsastoIds = Arrays.asList(1L);
        Page<Tuote> tuotteetPage = tuoteService.searchTuotteetByNimiAndOsasto(keyword, kahvilaitteetOsastoIds,
                pageable);

        long totalKahvilaitteet = tuoteService.countTotalProducts(kahvilaitteetOsastoIds);

        model.addAttribute("tuotteet", tuotteetPage.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tuotteetPage.getTotalPages());
        model.addAttribute("totalKahvilaitteet", totalKahvilaitteet);
        model.addAttribute("sortDir", sortDir);
        return "kahvilaitteet";
    }

    @GetMapping("/searchKulutustuotteet")
    public String searchKulutustuotteet(@RequestParam("keyword") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            Model model) {

        Sort.Direction direction = Sort.Direction.fromString(sortDir);
        Sort sort = Sort.by(direction, "hinta");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<Long> kulutustuotteetOsastoIds = Arrays.asList(2L, 7L);
        Page<Tuote> tuotteetPage = tuoteService.searchTuotteetByNimiAndOsasto(keyword, kulutustuotteetOsastoIds,
                pageable);
        model.addAttribute("tuotteet", tuotteetPage.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tuotteetPage.getTotalPages());
        model.addAttribute("sortDir", sortDir);
        return "kulutustuotteet";
    }

    @PostMapping("/poistaTuote/{id}")
    public String poistaTuote(@PathVariable Long id) {
        tuoteService.deleteTuote(id);
        return "redirect:/admin";
    }

}
