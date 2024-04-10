package com.example;

import com.example.hashset.Counter;
import com.example.hashset.Customer;
import com.example.hashset.MyHashSet;

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

        // metodo statico
        MyHashSet.sum(2, 2);
        example.clearSet();

        // Creazione di un oggetto Customer
        Customer customer = new Customer(1, "John Doe");
        customer.getId();
        customer.getName();
        
        // new class Counter
        Counter c1, c2;
        c1 = new Counter();
        c1.inc(); 
        c1.inc(); 
        c1.getValue();
        c2 = new Counter();
        c2.getValue();
        c1.copy(c2);
        c1.getValue();
        c1.inc();
        c1.inc();
        c1.inc();
        c1.getValue();
        c1.reset();
        c1.getValue();
        

    }
}
