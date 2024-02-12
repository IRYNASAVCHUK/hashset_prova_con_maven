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
        // Customer persone1 = new Customer(0, "Rajeev") ;
        // Customer persone2 = new Customer(1, "Sachin") ;
        // Customer persone3 = new Customer(2,"Chris") ;
        // example.addCustomer(persone1);
        // example.addCustomer(persone2);
        // example.addCustomer(persone3);
        // example.addCustomer(persone3);
        // example.removeCustomer(persone1);
        // example.removeCustomer(persone1);
        // example.containsCustomer(persone3);
        // example.getSize();
        // example.clearSet();
    }
}
