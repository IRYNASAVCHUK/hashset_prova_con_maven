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
	}

}
