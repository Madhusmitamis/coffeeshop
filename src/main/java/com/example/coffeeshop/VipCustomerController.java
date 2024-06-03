package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VipCustomerController {

    @Autowired
    private VipCustomerService vipCustomerService;

    @GetMapping("/allVipCustomers")
    public String allVipcustomer(Model model) {
        List<VipCustomer> vipCustomers = vipCustomerService.findAll();
        model.addAttribute("vipCustomers", vipCustomers);
        return "allVipCustomers";
    }

    @GetMapping("/vipCustomer")
    public String vipCustomer() {
        return "vipCustomer";
    }

    @PostMapping("/vipCustomer")
    public String registerVipCustomers(@RequestParam String etunimi, @RequestParam String sukunimi,
            @RequestParam String sahkopostiosoite) {
        VipCustomer existingVipCustomer = vipCustomerService.findByEmail(sahkopostiosoite);
        if (existingVipCustomer != null)
            return "redirect:/vipCustomer";

        vipCustomerService.registerVipCustomers(etunimi, sukunimi, sahkopostiosoite);

        return "redirect:/allVipCustomers";
    }

    // @GetMapping("/muokkaa/{id}")
    // public String editVIPCustomer(@PathVariable Long id, Model model) {
    // VipCustomers vipCustomer = vipCustomersService.findById(id);
    // if (vipCustomer != null) {
    // model.addAttribute("vipCustomer", vipCustomer);
    // return "vipcustomerForm";
    // }
    // return "redirect:/vipCustomers";
    // }

    @GetMapping("/poista/{id}")
    public String deleteVIPCustomer(@PathVariable Long id) {
        vipCustomerService.deleteById(id);
        return "redirect:/allVipCustomers";
    }
}
