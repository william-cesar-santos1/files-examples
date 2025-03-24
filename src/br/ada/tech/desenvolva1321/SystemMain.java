package br.ada.tech.desenvolva1321;

import br.ada.tech.desenvolva1321.persistence.text.CustomerFilePersistence;
import br.ada.tech.desenvolva1321.service.impl.CustomerService;
import br.ada.tech.desenvolva1321.view.CustomerView;

public class SystemMain {

    public static void main(String[] args) {
        registerAll();
        var customerPersistence = new CustomerFilePersistence();
        var customerService = new CustomerService(customerPersistence, customerPersistence);
        var view = new CustomerView(customerService, customerService);
        view.show();
    }

    private static void registerAll() {
        var persistence = new CustomerFilePersistence();
        DataProduces.customers()
                .forEach(persistence::save);
    }
}
