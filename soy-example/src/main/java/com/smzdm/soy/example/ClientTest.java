package com.smzdm.soy.example;


import com.smzdm.soy.http.annotation.Header;
import com.smzdm.soy.http.annotation.Http;
import com.smzdm.soy.http.annotation.Param;
import com.smzdm.soy.http.annotation.PathParam;
import com.smzdm.soy.http.domain.Method;

import java.util.Date;
import java.util.Map;

import static com.smzdm.soy.util.BuiltIn.Serialize;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public interface ClientTest {
    @Http(method = Method.GET, path = "/hello", serialize = Serialize.JSON, responseSerialize = Serialize.JSON)
    Reply sayHi(@Param("greeting") Greeting greeting,
                @Param("param1") String param1,
                @Param("param2") String param2,
                @Param Map<String, String> params,
                @Header Map<String, String> headers,
                @PathParam Map<String, String> pathParam);



    Student student();

}
