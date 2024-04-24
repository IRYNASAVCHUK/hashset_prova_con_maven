package com.example;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

class CAdapter2 extends TypeAdapter<C> {

	private int level;
	private Gson gson;

	CAdapter2(int level) {
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

	public Gson getGson() {
		return gson;
	}

	public void setGson(GsonBuilder builder) {
		this.gson = builder.create();
	}

}
