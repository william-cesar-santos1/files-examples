package br.ada.tech.desenvolva1321.view;

import br.ada.tech.desenvolva1321.model.Customer;
import br.ada.tech.desenvolva1321.service.RegisterCustomerUseCase;
import br.ada.tech.desenvolva1321.service.SearchCustomerUseCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Scanner;

public class CustomerView {

    private SearchCustomerUseCase searchUseCase;
    private RegisterCustomerUseCase registerUseCase;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CustomerView(SearchCustomerUseCase searchUseCase, RegisterCustomerUseCase registerUseCase) {
        this.searchUseCase = searchUseCase;
        this.registerUseCase = registerUseCase;
    }

    public void show() {
        try (Scanner scanner = new Scanner(System.in)) {
            menu(scanner);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Infelizmente ocorreu um erro no sistema. Tente novamente mais tarde");
        }
    }

    private void menu(Scanner scanner) {
        String option = "-1";
        do {
            System.out.println("Escolha uma opção");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar por id");
            System.out.println("3 - Listar todos");
            option = scanner.nextLine();
            switch (option) {
                case "0" -> System.out.println("Até mais....");
                case "1" -> showRegister(scanner);
                case "2" -> showSearch(scanner);
                case "3" -> showAllCustomer();
                default -> System.out.println("Invalid option. Try again");
            }
        } while (!option.equalsIgnoreCase("0"));
    }

    private void showAllCustomer() {
        printHeader();
        var customers = searchUseCase.list();
        customers.sort(Comparator.comparing(Customer::getId));
        customers.forEach(this::printCustomer);
    }

    private void showSearch(Scanner scanner) {
        System.out.println("Informe o id do cliente");
        var id = Long.parseLong(scanner.nextLine());
        searchUseCase.findById(id)
                .ifPresentOrElse(customer -> {
                    printHeader();
                    printCustomer(customer);
                }, () -> System.out.println("Nenhum cliente encontrado"));
    }

    private void showRegister(Scanner scanner) {
        System.out.println("Informe o nome do cliente");
        var name = scanner.nextLine();
        System.out.println("Informe o documento do cliente");
        var document = scanner.nextLine();
        System.out.println("Informe a data de nascimento(dd/mm/yyyy)");
        var birthdate = scanner.nextLine();
        var customer = new Customer(null, name, document, LocalDate.parse(birthdate, DATE_FORMATTER));
        registerUseCase.save(customer);
        printHeader();
        printCustomer(customer);
    }

    private void printHeader() {
        System.out.print(padRight("Id", 3));
        System.out.print(" | ");
        System.out.print(padRight("Name", 20));
        System.out.print(" | ");
        System.out.print(padRight("Document", 15));
        System.out.print(" | ");
        System.out.print(padRight("Birth date", 10));
        System.out.println(" |");
    }

    private void printCustomer(Customer customer) {
        System.out.print(padRight(customer.getId().toString(), 3));
        System.out.print(" | ");
        System.out.print(padRight(customer.getName(), 20));
        System.out.print(" | ");
        System.out.print(padRight(customer.getDocument(), 15));
        System.out.print(" | ");
        System.out.print(padRight(customer.getBirthdate().format(DATE_FORMATTER), 10));
        System.out.println(" |");
    }

    private String padRight(String value, int size) {
        return String.format("%-" + size + "s", value);
    }
}
