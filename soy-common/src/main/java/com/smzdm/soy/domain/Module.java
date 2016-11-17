package com.smzdm.soy.domain;

import com.smzdm.soy.AbstractModule;
import com.smzdm.soy.domain.impl.JsonCodec;

/**
 * Created by zhengwenzhu on 2016/11/17.
 */
public class Module extends AbstractModule{

    @Override
    public void init() {
        CodecFactory.registerCodec(new JsonCodec());
    }
}
