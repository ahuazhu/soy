package com.smzdm.soy.http.annotation;


import com.smzdm.soy.http.domain.Method;
import com.smzdm.soy.util.BuiltIn;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public @interface Http {
    Method method() default Method.GET;

    String path() default "";

    BuiltIn.Serialize serialize() default BuiltIn.Serialize.JSON;

    BuiltIn.Serialize responseSerialize() default BuiltIn.Serialize.JSON;
}
