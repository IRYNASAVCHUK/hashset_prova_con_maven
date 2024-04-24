package com.example;

import com.google.gson.GsonBuilder;

public class Test {

	public static void main(String[] args) {
		final var level = 4;
		var c = new C(new C(new C(null, null), new C(null, null)), new C(new C(null, null), new C(null, null)));
		var builder1 = new GsonBuilder();
		var adapter1 = new CAdapter1(level);
		builder1.registerTypeAdapter(C.class, adapter1).setPrettyPrinting();
		var gson = builder1.create();
		System.out.println(gson.toJson(c));
		
		var builder2 = new GsonBuilder();
		var adapter2 = new CAdapter2(level);
		builder2.registerTypeAdapter(C.class, adapter2).setPrettyPrinting();
		adapter2.setGson(builder2);
		System.out.println(adapter2.getGson().toJson(c));
		
		var builder3 = new GsonBuilder();
		var adapter3 = new CAdapter3(level);
		builder3.registerTypeAdapter(C.class, adapter3).setPrettyPrinting();
		adapter3.setGson(builder3);
		System.out.println(adapter3.getGson().toJson(c));
	}

}
