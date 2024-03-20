package com.example.hashset;

public class Main {
	public static void main(String[] args) {
		Test.st();
		final Test t1=new Test().m();
		final Test t2=new Test().m();
		final Test t3=new Test().m();
		t1.getId();
		t2.getId();
		t3.getId();


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

		// metodo statico
		MyHashSet.sum(2, 2);
		example.clearSet();

		// Creazione di un oggetto Customer
		Customer customer = new Customer(1, "John Doe");
		assert customer.getId() == 1 : "ID non corrisponde";
		assert customer.getName().equals("John Doe") : "Nome non corrisponde";

	}
}
