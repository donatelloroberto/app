package com.amazon.cptplugins.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;

class BooleanDeserializer implements JsonDeserializer<Boolean> {
    BooleanDeserializer() {
    }

    public Boolean deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) {
        return Boolean.valueOf(getValue(jsonElement).toLowerCase());
    }

    private String getValue(JsonElement jsonElement) {
        return jsonElement.getAsJsonPrimitive().getAsString();
    }
}
