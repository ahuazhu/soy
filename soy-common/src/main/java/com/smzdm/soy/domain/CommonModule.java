package com.smzdm.soy.domain;

import com.smzdm.soy.Module;
import com.smzdm.soy.domain.impl.JsonCodec;

/**
 * Created by zhengwenzhu on 2016/11/17.
 */
public class CommonModule implements Module {
    public void init() {
        CodecFactory.registerCodec(new JsonCodec());
    }

    public void shutdown() {

    }

}
