package com.smzdm.soy.http.annotation;


import com.smzdm.soy.http.domain.Method;
import com.smzdm.soy.util.BuiltIn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengwenzhu on 16/10/28.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Http {
    Method method() default Method.GET;

    String path() default "";

    BuiltIn.Serialize serialize() default BuiltIn.Serialize.JSON;

    BuiltIn.Serialize responseSerialize() default BuiltIn.Serialize.JSON;
}
