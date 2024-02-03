package com.example.hashset;

public class Main {

    public static void main(String[] args) {
        
        final MyHashSet example = new MyHashSet();
        Customer persone1 = new Customer(0, "Rajeev") ;
        example.addCustomer(persone1);
        example.addCustomer(1,"Sachin");
        example.addCustomer(2,"Chris");
        boolean a = example.addCustomer(0,"Rajeev");
        System.out.println(a);

        System.out.println("\nCustomers: " + example.customers + "\n");

        boolean b = example.removeCustomer(0,"Rajeev");
        System.out.println(b);
        boolean c = example.removeCustomer(0, "Rajeev");
        System.out.println(c);
        System.out.println("\nCustomers after removing customer with ID 0: " + example.customers + "\n");

        System.out.println("\nContains customer with ID 2: " + example.containsCustomer(2, "Chris"));

        System.out.println("\nSize of the set: " + example.getSize());

        example.clearSet();
        System.out.println("\nCustomers after clearing the set: " + example.customers + "\n");
    }
}
