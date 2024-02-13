package com.example.hashset;

public class Main {
    public static void main(String[] args) {
        final MyHashSet example = new MyHashSet();
        Customer person1 = new Customer(0, "Mario");
        Customer person2 = new Customer(1, "Marco");
        Customer person3 = new Customer(2, "Matteo");
        example.addCustomer(person1);
        example.addCustomer(person2);
        example.addCustomer(person3);
        example.addCustomer(person3);
        example.removeCustomer(person1);
        example.removeCustomer(person1);
        example.containsCustomer(person3);
        example.getSize();
        example.clearSet();
    }
}
