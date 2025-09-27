package com.myapp.section1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.section1.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	 Optional<Customer> findByEmail(String email);
}
