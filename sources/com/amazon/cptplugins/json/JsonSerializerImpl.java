package com.amazon.cptplugins.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

public class JsonSerializerImpl implements JsonSerializer {
    private final Gson gson;

    public JsonSerializerImpl() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Boolean.class, new BooleanDeserializer());
        this.gson = builder.create();
    }

    public <T> T fromJson(String json, Type typeOfT) {
        return this.gson.fromJson(json, typeOfT);
    }

    public String toJson(Object src) {
        return this.gson.toJson(src);
    }
}
