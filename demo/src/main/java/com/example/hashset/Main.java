package com.example.hashset;

import java.util.Arrays;

import com.example.logger.MyRecord;

public class Main {
    public static void main(String[] args) {
        // Creazione di un oggetto Result usando il record
        MyRecord<String> result = new MyRecord<>(String.class, "Success", new Object[]{"param1", "param2"});

        // Accesso ai campi dell'oggetto Result
        System.out.println("Return Type: " + result.returnType());
        System.out.println("Result: " + result.result());
        System.out.println("Params: " + Arrays.toString(result.params()));
        
        // int intRes = 42 ;
        // boolean boolRes = true;
    
        // int j = 0;
        // boolean b = false;
        // Class<Boolean> c = boolean.class;
        // if (c.isPrimitive())
        //         switch (c.getTypeName()) {
        //         case "int":
        //                 j = (int) intRes;
        //                 break;
        //         case "boolean":
        //                 b = (boolean) boolRes;
        //         }
        // System.out.println(j);
        // System.out.println(b);




        // final MyHashSet example = new MyHashSet();
        // Customer person1 = new Customer(0, "Rajeev") ;
        // Customer person2 = new Customer(1, "Sachin") ;
        // Customer person3 = new Customer(2,"Chris") ;
        // example.addCustomer(person1);
        // example.addCustomer(person2);
        // example.addCustomer(person3);
        // example.addCustomer(person3);
        // example.removeCustomer(person1);
        // example.removeCustomer(person1);
        // example.containsCustomer(person3);
        // example.getSize();
        // example.clearSet();
    }
}
