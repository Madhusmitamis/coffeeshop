package com.example.coffeeshop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VipCustomerRepository extends JpaRepository<VipCustomer, Long> {
    VipCustomer findBySahkopostiosoite(String sahkopostiosoite);
}
