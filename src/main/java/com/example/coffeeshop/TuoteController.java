package com.example.coffeeshop;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TuoteController {
    @Autowired
    private ToimittajaRepository toimittajaRepository;
    @Autowired
    private ValmistajaRepository valmistajaRepository;
    @Autowired
    private OsastoRepository osastoRepository;
    @Autowired
    private TuoteRepository tuoteRepository;

    @GetMapping("/admin")
    public String tuote(Model model) {
        model.addAttribute("osastot", this.osastoRepository.findAll());
        model.addAttribute("toimittajat", this.toimittajaRepository.findAll());
        model.addAttribute("valmistajat", this.valmistajaRepository.findAll());
        List<Tuote> tuotteet = this.tuoteRepository.findAll();
        model.addAttribute("tuotteet", tuotteet);
        return "admin";
    }

    @PostMapping("/admin")
    public String addTuote(@RequestParam String nimi, @RequestParam String kuvaus, @RequestParam BigDecimal hinta,
            // @RequestParam String tuotekuva,
            @RequestParam Long toimittajaid, @RequestParam Long valmistajaid,
            @RequestParam Long osastoid, @RequestParam("kuva") MultipartFile file) {
        if (tuoteRepository.existsByNimi(nimi)) {
            return "redirect:/admin?error=DuplicateName";
        }
        Tuote tuote = new Tuote();
        tuote.setNimi(nimi);
        tuote.setKuvaus(kuvaus);
        tuote.setHinta(hinta);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Save the file to your desired location or store it in the database
                // For example:
                // Path path = Paths.get("path/to/your/directory/" +
                // file.getOriginalFilename());
                // Files.write(path, bytes);
                tuote.setTuotekuva(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle file upload error
            }
        }
        // tuote.setTuotekuva(tuotekuva);
        Valmistaja v = valmistajaRepository.findById(valmistajaid).orElse(null);
        tuote.setValmistaja(v);
        Osasto o = osastoRepository.findById(osastoid).orElse(null);
        tuote.setOsasto(o);
        Toimittaja t = toimittajaRepository.findById(toimittajaid).orElse(null);
        tuote.setToimittaja(t);

        this.tuoteRepository.save(tuote);
        return "redirect:/admin";

    }

    // @PostMapping("/admin/{tuoteId}/toimittajat/{toimittajaId}/valmistajat{valmistajaId}/osastot{osastoId}")
    // public String addOmistaja(@PathVariable Long touteId, @PathVariable Long
    // toimittajaId,
    // @PathVariable Long valmistajaId, @PathVariable Long osastoId) {
    // Tuote tuote = tuoteRepository.getOne(tuoteId);
    // Toimittaja toimittaja = toimittajaRepository.getOne(toimittajaId);
    // Valmistaja valmistaja = valmistajaRepository.getOne(valmistajaId);
    // Osasto osasto = osastoRepository.getOne(osastoId);

    // tuote.getTuotteet().add(tuote);
    // touteRepository.save(tuote);

    // return "redirect:/admin/" + tuoteId;
    // }

}
