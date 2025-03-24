package br.ada.tech.desenvolva1321.service;

import br.ada.tech.desenvolva1321.model.Customer;

import java.util.List;
import java.util.Optional;

public interface SearchCustomerUseCase {

    Optional<Customer> findById(Long id);

    List<Customer> list();

}
