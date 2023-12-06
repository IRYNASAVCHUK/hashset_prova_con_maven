package com.example.hashset;

public class Main {

    public static void main(String[] args) {
        final MyHashSet example = new MyHashSet();

        example.addCustomer("Rajeev");
        example.addCustomer("Sachin");
        example.addCustomer("Chris");

        example.addCustomer("Rajeev");

        System.out.println("Customers: " + example.customers + "\n");

        example.removeCustomer(1);
        System.out.println("Customers after removing customer with ID 1: " + example.customers + "\n");

        System.out.println("Contains customer with ID 2: " + example.containsCustomer(2));

        System.out.println("Size of the set: " + example.getSize());

        example.clearSet();
        System.out.println("Customers after clearing the set: " + example.customers + "\n");
    }
}
