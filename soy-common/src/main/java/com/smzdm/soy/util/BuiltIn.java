package com.smzdm.soy.util;

/**
 * Created by zhengwenzhu on 16/10/27.
 */
public class BuiltIn {

    public enum Proto {
        HTTP,
    }

    public enum CallType {
        Sync,
        ONEWAY,
    }

    public enum Serialize {
        JSON,
        XML,
        RAW,
        FORM
    }
}
