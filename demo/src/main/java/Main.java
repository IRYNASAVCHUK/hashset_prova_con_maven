


import java.util.*;

import helper.*;
import helper.animal_hierarchy.Animal;
import helper.animal_hierarchy.BabyCat;
import helper.animal_hierarchy.Cat;
import helper.set.*;

public class Main {
    public static void main(String[] args) {
        var person1 = new Customer(0, "Mario");
        person1.getId();
        person1.setId(1000);
        person1.getName();
        var person2 = new Customer(1, "Marco");
        person2.getId();
        person2.getName();
        var person3 = new CustomerWithAddress(2, "Matte", "via XX September 20");
        person3.getId();
        person3.getName();
        var example = new MyList();
        example.getCustomers();
        example.addCustomer(person1);
        example.addCustomer(person2);
        example.addCustomer(person3);
        example.addCustomer(person3);
        example.getCustomers();

        List<Customer> customers = new ArrayList<>();
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
        example.clearSet();

        //static method
        MyList.sum(2, 2);

        // new class Counter Simple
        var c1 = new Counter();
        c1.inc();
        c1.inc();
        c1.getVal();
        var c2 = new Counter();
        c2.getVal();
        c1.copy(c2);
        c1.getVal();
        c1.reset();
        c1.setVal(100);
        c1.getVal();

        new C(new C(new C(null, null), new C(null, null)), new C(new C(null, null), new C(null, null)));

        //Inheritance Class
        Animal animal = new Animal(true, "vegetables", 4);
        animal.isVegetarian();
        animal.getEats();
        animal.getNoOfLegs();

        Cat cat = new Cat(false, "meat", 4,"Black");
        cat.isVegetarian();
        cat.getEats();
        cat.getNoOfLegs();
        cat.getColor();

        BabyCat babyCat = new BabyCat(false, "milk", 4,"white", "meow");
        babyCat.isVegetarian();
        babyCat.getEats();
        babyCat.getNoOfLegs();
        babyCat.getColor();
        babyCat.getSound();
    }
}
