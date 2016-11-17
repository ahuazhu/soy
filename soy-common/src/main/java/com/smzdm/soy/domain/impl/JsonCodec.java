package com.smzdm.soy.domain.impl;

import com.google.gson.Gson;
import com.smzdm.soy.domain.Codec;

/**
 * Created by zhengwenzhu on 2016/11/17.
 */
public class JsonCodec implements Codec {

    private static final String NAME = "Json";

    public String name() {
        return NAME;
    }

    public <T> String encode(T o) {
        return new Gson().toJson(o);
    }

    public <T> T decode(String str, Class<T> clazz) {
        return new Gson().fromJson(str, clazz);
    }
}
