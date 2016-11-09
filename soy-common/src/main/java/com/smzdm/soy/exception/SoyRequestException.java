package com.smzdm.soy.exception;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public class SoyRequestException extends SoyException {
    public SoyRequestException(Throwable e) {
        super(e);
    }

    public SoyRequestException() {
        super();
    }

    public SoyRequestException(String msg) {
        super(msg);
    }
}
