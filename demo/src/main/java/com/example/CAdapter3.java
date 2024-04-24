package com.example;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/* 
 * even less efficient and much more involved version
 * the only advantage is that the adapter is stateless
 */

class CAdapter3 extends TypeAdapter<C> {

	private final int level;
	                    /*
						 * it would be better to directly store a builder, 
						 * but the only way to get a copy of a builder 
						 * is through a Gson object with newBuilder() 
						 */
	private Gson gson;

	CAdapter3(int level) {
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
			var builder = gson.newBuilder();
			var newAdapter = new CAdapter3(level-1);
			builder.registerTypeAdapter(C.class, newAdapter);
			newAdapter.setGson(builder);
			var gson = newAdapter.getGson();
			out.name("f1");
			out.jsonValue(gson.toJson(value.f1));
			out.name("f2");
			out.jsonValue(gson.toJson(value.f2));
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
