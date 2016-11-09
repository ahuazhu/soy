package com.smzdm.soy.invoker;

import com.smzdm.soy.domain.InvokerConfig;
import com.smzdm.soy.util.BuiltIn;

/**
 * Created by zhengwenzhu on 2016/10/31.
 */
public class ServiceFactory {

    public static <S> S getService(InvokerConfig<S> config) {
        BuiltIn.Serialize serialize = config.getSerialize();
        return null;

    }
}
