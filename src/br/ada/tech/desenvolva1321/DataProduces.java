package br.ada.tech.desenvolva1321;

import br.ada.tech.desenvolva1321.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataProduces {

    public static List<Customer> customers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "Alice Johnson", "1234567890", LocalDate.of(1985, 11, 15)));
        customers.add(new Customer(2L, "Bob Smith", "9876543210", LocalDate.of(1990, 5, 20)));
        customers.add(new Customer(3L, "Bob Brown", "5555555555", LocalDate.of(1975, 9, 30)));
        customers.add(new Customer(4L, "David Lee", "4444444444", LocalDate.of(1995, 2, 10)));
        customers.add(new Customer(5L, "Emily Davis", "3333333333", LocalDate.of(1988, 7, 25)));
        customers.add(new Customer(6L, "Frank Wilson", "2222222222", LocalDate.of(1970, 12, 5)));
        customers.add(new Customer(7L, "Grace Miller", "1111111111", LocalDate.of(1992, 3, 18)));
        customers.add(new Customer(8L, "Henry Baker", "0000000000", LocalDate.of(1982, 8, 7)));
        customers.add(new Customer(9L, "Isabella Green", "9999999999", LocalDate.of(1997, 4, 12)));
        customers.add(new Customer(10L, "Jack Taylor", "8888888888", LocalDate.of(1978, 6, 23)));
        customers.add(new Customer(11L, "Alice Cooper", "156143541695", LocalDate.of(1978, 6, 23)));
        return customers;
    }

}
