package com.example.hashset;

public class Main {
    public static void main(String[] args) {
        final MyHashSet example = new MyHashSet();
        Customer persone1 = new Customer(0, "Rajeev") ;
        Customer persone2 = new Customer(1, "Sachin") ;
        Customer persone3 = new Customer(2,"Chris") ;
        example.addCustomer(persone1);
        example.addCustomer(persone2);
        example.addCustomer(persone3);
        example.addCustomer(persone3);
        example.removeCustomer(persone1);
        example.removeCustomer(persone1);
        example.containsCustomer(persone3);
        example.getSize();
        example.clearSet();
    }
}
