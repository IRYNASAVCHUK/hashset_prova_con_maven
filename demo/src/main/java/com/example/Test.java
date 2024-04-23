package com.example;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

class C {
	C f1;
	C f2;

	C(C f1, C f2) {
		this.f1 = f1;
		this.f2 = f2;
	}

}

class CAdapter extends TypeAdapter<C> {

	int level;
	Gson gson;

	CAdapter(int level) {
		this.level = level;
	}

	@Override
	public void write(JsonWriter out, C value) throws IOException {
//		if (value == null) {
//			out.jsonValue(null);
//			return;
//		}
		out.beginObject();
		out.name("@");
		out.value(System.identityHashCode(value));
		out.name("class");
		out.value(value.getClass().getCanonicalName());
		if (level > 0) {
			level--;
			out.name("f1");
			out.jsonValue(gson.toJson(value.f1));
			out.name("f2");
			out.jsonValue(gson.toJson(value.f2));
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
		var c = new C(new C(new C(null, null), new C(null, null)), new C(new C(null, null), new C(null, null)));
		var builder = new GsonBuilder();
		var adapter = new CAdapter(4);
		builder.registerTypeAdapter(C.class, adapter).setPrettyPrinting();
		adapter.gson = builder.create();
		System.out.println(adapter.gson.toJson(c));
	}

}
