package com.smzdm.soy.domain;

/**
 * Created by zhengwenzhu on 2016/11/17.
 */
public interface Codec {

    String name();

    <T> String encode(T o);

    <T> T decode(String str, Class<T> clazz);
}
