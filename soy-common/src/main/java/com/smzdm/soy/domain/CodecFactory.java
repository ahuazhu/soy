package com.smzdm.soy.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhengwenzhu on 2016/11/17.
 */
public class CodecFactory {

    private static Map<String, Codec> codecMap = new ConcurrentHashMap<String, Codec>();

    public static Codec getCodec(String name) {
        return codecMap.get(name);
    }

    public static void registerCodec(Codec codec) {
        codecMap.put(codec.name(), codec);
    }
}
