package com.example;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

class C {
	C f;

	C(C f) {
		this.f = f;
	}

}

class CAdapter extends TypeAdapter<C> {

	int level;

	CAdapter(int level) {
		this.level = level;
	}

	@Override
	public void write(JsonWriter out, C value) throws IOException {
		out.beginObject();
		out.name("@");
		out.value(System.identityHashCode(value));
		out.name("class");
		out.value(value.getClass().getCanonicalName());
		if (level > 0) {
			level--;
			out.name("f");
			var builder = new GsonBuilder();
			builder.registerTypeAdapter(C.class, new CAdapter(level));
			var gson = builder.create();
			out.jsonValue(gson.toJson(value.f));
			level++;
		}
		out.endObject();
	}

	@Override
	public C read(JsonReader in) throws IOException {
		throw new UnsupportedOperationException();
	}

}

public class Test {

	public static void main(String[] args) {
		var c = new C(new C(new C(null)));
		var builder = new GsonBuilder();
		builder.registerTypeAdapter(C.class, new CAdapter(4));
		var gson = builder.create();
		System.out.println(gson.toJson(c));
	}

}
