package com.example.coffeeshop;

import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoffeeshopController {
    @Autowired
    private TuoteService tuoteService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/kahvilaitteet")
    public String kahvilaitteet(Model model, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "asc") String sortDir) {

        int pageSize = 6;
        Sort.Direction direction = Sort.Direction.fromString(sortDir);
        Sort sort = Sort.by(direction, "hinta");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Tuote> tuotePage = tuoteService.getProductForKahvilaitteet(pageable);

        model.addAttribute("totalPages", tuotePage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageNumber", page + 1);
        model.addAttribute("tuotteet", tuotePage.getContent());
        model.addAttribute("sortDir", sortDir);

        List<Long> kahvilaitteetOsastoIds = Arrays.asList(1L);
        long totalKahvilaitteet = tuoteService.countTotalProducts(kahvilaitteetOsastoIds);
        model.addAttribute("totalKahvilaitteet", totalKahvilaitteet);

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
    public String kulutustuotteet(Model model, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "asc") String sortDir) {
        int pageSize = 6;
        // Sort sort = Sort.by(Sort.Direction.DESC, "hinta");

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), "hinta");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Tuote> tuotePage = tuoteService.getProductForKulutustuotteet(pageable);

        model.addAttribute("totalPages", tuotePage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageNumber", page + 1);
        model.addAttribute("tuotteet", tuotePage.getContent());
        model.addAttribute("sortDir", sortDir);

        List<Long> kulutustuotteetOsastoIds = Arrays.asList(2L, 7L);
        long totalKulutustuotteet = tuoteService.countTotalProducts(kulutustuotteetOsastoIds);
        model.addAttribute("totalKulutustuotteet", totalKulutustuotteet);

        return "kulutustuotteet";
    }

}
