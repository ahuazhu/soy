package com.smzdm.soy.exception;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public class SoyException extends RuntimeException {
    public SoyException(Throwable e) {
        super(e);
    }

    public SoyException() {
        super();
    }

    public SoyException(String msg) {
        super(msg);
    }
}
