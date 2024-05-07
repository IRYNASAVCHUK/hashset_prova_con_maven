package com.example;

import com.example.set.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Customer person1 = new Customer(0, "Mario");
        person1.getId();
        person1.getName();
        Customer person2 = new Customer(1, "Marco");
        person2.getId();
        person2.getName();
        Customer person3 = new Customer(2, "Matteo");
        person3.getId();
        person3.getName();
        final MySet example = new MySet();
        example.getCustomers();
        example.addCustomer(person1);
        example.addCustomer(person2);
        example.addCustomer(person3);
        example.addCustomer(person3);
        example.getCustomers();

        Set<Customer> customers = new HashSet<>();
        customers.add(person1);
        customers.add(person2);
        customers.add(person3);
        customers.add(person3);
        example.setCustomers(customers);
        example.getCustomers();
        example.getSize();

        example.removeCustomer(person1);
        example.removeCustomer(person1);
        example.containsCustomer(person3);
        example.getSize();

        // metodo statico
        MySet.sum(2, 2);
        example.clearSet();

        // new class Counter
        Counter c1, c2;
        c1 = new Counter();
        c1.inc();
        c1.inc();
        c1.getVal();
        c2 = new Counter();
        c2.getVal();
        c1.copy(c2);
        c1.getVal();
        c1.reset();
        c1.setVal(100);
        c1.getVal();
        // var c = new C(
        //     new C(new C(null, null), new C(null, null)), 
        //     new C(new C(null, null), new C(null, null)));
    }
}
