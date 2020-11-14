package br.com.ilegra.domain.seller;

public class Seller {

    private final String cpf;
    private final String name;
    private final double salary;

    public Seller(String cpf, String name, double salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

}
