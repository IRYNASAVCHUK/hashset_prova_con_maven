package com.example.hashset;

public class Main {
    public static void main(String[] args) {
        final MyHashSet example = new MyHashSet();
        Customer person1 = new Customer(0, "Mario");
        Customer person2 = new Customer(1, "Marco");
        Customer person3 = new Customer(2, "Matteo");
        assert example.addCustomer(person1);
        assert example.addCustomer(person2);
        assert example.addCustomer(person3);
        assert !example.addCustomer(person3); 
        assert example.removeCustomer(person1);
        assert !example.removeCustomer(person1);
        assert example.containsCustomer(person3);
        assert example.getSize() == 2;
        // metodi statici
        MyHashSet.containsAnyCustomerWithName(example, "Mario");
        MyHashSet.countCustomersWithIdGreaterThan(example, 0);
        //assert con metodi statici non funzionano
        // assert MyHashSet.containsAnyCustomerWithName(example, "Mario");
        // assert MyHashSet.countCustomersWithIdGreaterThan(example, 0)==1;
        example.clearSet();
    }
}
