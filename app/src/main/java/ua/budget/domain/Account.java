package ua.budget.domain;

public class Account {
    public Integer id;
    public String name;
    public Double amount;

    public Account(Integer id, String name, Double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }
}
