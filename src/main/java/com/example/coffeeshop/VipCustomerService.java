package com.example.coffeeshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipCustomerService {
    @Autowired
    VipCustomerRepository vipCustomerRepository;

    public List<VipCustomer> findAll() {
        return vipCustomerRepository.findAll();
    }

    public VipCustomer findByEmail(String sahkopostiosoite) {
        return vipCustomerRepository.findBySahkopostiosoite(sahkopostiosoite);
    }

    public VipCustomer registerVipCustomers(String etunimi, String sukunimi, String sahkopostiosoite) {
        VipCustomer vipCustomer = new VipCustomer();
        vipCustomer.setEtunimi(etunimi);
        vipCustomer.setSukunimi(sukunimi);
        vipCustomer.setSahkopostiosoite(sahkopostiosoite);

        return vipCustomerRepository.save(vipCustomer);
    }

    public void deleteById(Long id) {
        vipCustomerRepository.deleteById(id);
    }

    public VipCustomer findById(Long id) {
        return vipCustomerRepository.findById(id).orElse(null);
    }

}
