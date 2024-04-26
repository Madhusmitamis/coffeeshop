package com.example.coffeeshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoffeeshopController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/kahvilaitteet")
    public String devices() {
        return "kahvilaitteet";
    }

    // @GetMapping("/osasto")
    // public String osasto(Model model) {
    // model.addAttribute("lists", osastoRepository.findAll());
    // return "osasto";

    // @PostMapping("/osasto")
    // public String create(@RequestParam String nimi, @RequestParam long osastoId)
    // {
    // osastoRepository.save(new Item(name,id)));
    // return "redirect:/";

}
