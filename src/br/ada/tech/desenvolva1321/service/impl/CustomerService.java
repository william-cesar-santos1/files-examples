package br.ada.tech.desenvolva1321.service.impl;

import br.ada.tech.desenvolva1321.model.Customer;
import br.ada.tech.desenvolva1321.persistence.Reader;
import br.ada.tech.desenvolva1321.persistence.Writer;
import br.ada.tech.desenvolva1321.service.RegisterCustomerUseCase;
import br.ada.tech.desenvolva1321.service.SearchCustomerUseCase;

import java.util.List;
import java.util.Optional;

public class CustomerService implements RegisterCustomerUseCase, SearchCustomerUseCase {

    private Writer<Customer> customerWriter;
    private Reader<Customer> customerReader;

    public CustomerService(Writer<Customer> customerWriter, Reader<Customer> customerReader) {
        this.customerWriter = customerWriter;
        this.customerReader = customerReader;
    }

    @Override
    public void save(Customer customer) {
        if (customer == null) {
            throw new IllegalStateException("Customer não pode ser nulo");
        }
        customerWriter.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id não pode ser nulo");
        }
        return customerReader.read(id);
    }

    @Override
    public List<Customer> list() {
        return customerReader.read();
    }

}
