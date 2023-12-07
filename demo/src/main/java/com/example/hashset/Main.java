package com.example.hashset;

public class Main {

    public static void main(String[] args) {
        
        final MyHashSet example = new MyHashSet();

        example.addCustomer(0,"Rajeev");
        example.addCustomer(1,"Sachin");
        example.addCustomer(2,"Chris");
        example.addCustomer(0,"Rajeev");

        System.out.println("\nCustomers: " + example.customers + "\n");

        example.removeCustomer(0);
        System.out.println("\nCustomers after removing customer with ID 0: " + example.customers + "\n");

        System.out.println("\nContains customer with ID 2: " + example.containsCustomer(2));

        System.out.println("\nSize of the set: " + example.getSize());

        example.clearSet();
        System.out.println("\nCustomers after clearing the set: " + example.customers + "\n");
    }
}
