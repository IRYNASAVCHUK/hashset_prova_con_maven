package com.example;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

// more efficient and simpler version
// but TypeAdapter.json is strict, therefore 
// some options of the builder are not effective (for instance)
class CAdapter1 extends TypeAdapter<C> {

	private int level;

	CAdapter1(int level) {
		this.level = level;
	}

	@Override
	public void write(JsonWriter out, C value) throws IOException {
		if (value == null) {
			out.jsonValue(null);
			return;
		}
		out.beginObject();
		out.name("@");
		out.value(System.identityHashCode(value));
		out.name("class");
		out.value(value.getClass().getCanonicalName());
		if (level > 0) {
			level--;
			out.name("f1");
			out.jsonValue(toJson(value.f1));
			out.name("f2");
			out.jsonValue(toJson(value.f2));
			level++;
		}
		out.endObject();
	}

	@Override
	public C read(JsonReader in) throws IOException {
		throw new UnsupportedOperationException();
	}

}
