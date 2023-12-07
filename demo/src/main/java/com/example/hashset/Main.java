package com.example.hashset;

public class Main {

    public static void main(String[] args) {
        
        final MyHashSet example = new MyHashSet();

        example.addCustomer("Rajeev");
        example.addCustomer("Sachin");
        example.addCustomer("Chris");

        example.addCustomer("Rajeev");

        System.out.println("\nCustomers: " + example.customers + "\n");

        example.removeCustomer(1);
        System.out.println("\nCustomers after removing customer with ID 1: " + example.customers + "\n");

        System.out.println("\nContains customer with ID 2: " + example.containsCustomer(2));

        System.out.println("\nSize of the set: " + example.getSize());

        example.clearSet();
        System.out.println("\nCustomers after clearing the set: " + example.customers + "\n");
    }
}
