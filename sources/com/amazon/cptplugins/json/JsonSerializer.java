package com.amazon.cptplugins.json;

import java.lang.reflect.Type;

public interface JsonSerializer {
    <T> T fromJson(String str, Type type);

    String toJson(Object obj);
}
