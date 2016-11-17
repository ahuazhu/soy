package com.smzdm.soy.example;

import com.smzdm.soy.domain.InvokerConfig;
import com.smzdm.soy.domain.impl.DefaultInvokerConfig;
import com.smzdm.soy.invoker.ServiceFactory;
import com.smzdm.soy.util.BuiltIn;

/**
 * Created by zhengwenzhu on 2016/11/9.
 */
public class Main {

    public static void main(String[] args) {

        InvokerConfig<ClientTest> config = makeConfig();


        ClientTest clientTest = ServiceFactory.getService(config);

        Student student = clientTest.student();

        System.out.print(student);

    }

    private static InvokerConfig<ClientTest> makeConfig() {
        InvokerConfig<ClientTest> config = new DefaultInvokerConfig<ClientTest>();

        config.setCallType(BuiltIn.CallType.Sync);
        config.setServiceName("http://localhost:8080");
        config.setSerialize(BuiltIn.Serialize.JSON);
        config.setTimeout(500);
        config.setProto(BuiltIn.Proto.HTTP);
        config.setInterface(ClientTest.class);
        return config;
    }
}
