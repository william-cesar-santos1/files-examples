package br.ada.tech.desenvolva1321.persistence.text;

import br.ada.tech.desenvolva1321.model.Customer;
import br.ada.tech.desenvolva1321.persistence.Reader;
import br.ada.tech.desenvolva1321.persistence.SearchByName;
import br.ada.tech.desenvolva1321.persistence.Writer;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class CustomerFilePersistence extends TextFile
        implements Writer<Customer>, Reader<Customer>, SearchByName<Customer> {

    private static final String ROOT_FOLDER = "./database/customers";
    private static final Function<String, Customer> READ_CONVERTER = (fileContent) -> {
        var fields = fileContent.split(CONTENT_SEPARATOR);
        var id = Long.parseLong(fields[0]);
        var name = fields[1];
        var document = fields[2];
        var birthdate = LocalDate.parse(fields[3]);
        return new Customer(id, name, document, birthdate);
    };
    private static final Function<Customer, String> WRITE_CONVERTER = (customer) -> {
        return customer.getId() + CONTENT_SEPARATOR
                + customer.getName() + CONTENT_SEPARATOR
                + customer.getDocument() + CONTENT_SEPARATOR
                + customer.getBirthdate() + CONTENT_SEPARATOR;
    };

    public CustomerFilePersistence() {
        super(ROOT_FOLDER);
    }

    @Override
    public Optional<Customer> read(Long id) {
        return getFile(id.toString())
                .filter(Files::exists)
                .map(this::readContent)
                .map(READ_CONVERTER);
    }

    @Override
    public Stream<Customer> read() {
        return filesInRootFolder()
                .map(this::readContent)
                .map(READ_CONVERTER);
    }

    @Override
    public Stream<Customer> search(String name) {
        return read().filter(customer -> customer.getName().contains(name));
    }

    @Override
    public void save(Customer customer) {
        fixId(customer);
        var content = WRITE_CONVERTER.apply(customer);
        var fileName = customer.getId().toString();
        var file = getFile(fileName)
                .filter(Files::exists)
                .orElse(createFile(fileName));
        write(file, content);
    }

    private void fixId(Customer customer) {
        if (customer.getId() == null) {
            var id = nextId().orElse(1l);
            customer.setId(id);
        }
    }

    private Optional<Long> nextId() {
        return filesInRootFolder()
                .map(file -> file.getFileName().toString())
                .map(fileName -> fileName.replaceAll(EXTENSION, ""))
                .map(Long::parseLong)
                .max(Long::compareTo)
                .map(value -> value + 1);
    }

}
