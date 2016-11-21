package com.smzdm.soy.example;

import com.smzdm.soy.domain.InvokerConfig;
import com.smzdm.soy.domain.impl.DefaultInvokerConfig;
import com.smzdm.soy.exception.SoyException;
import com.smzdm.soy.invoker.ServiceFactory;
import com.smzdm.soy.util.BuiltIn;

/**
 * Created by zhengwenzhu on 2016/11/9.
 */
public class Main {

    public static void main(String[] args) {

        InvokerConfig<ClientTest> config = makeConfig();


        ClientTest clientTest = ServiceFactory.getService(config);

        int success = 0;

        int fail =0;

        for (int i = 0; i < 100000; i ++) {
            try {
                Student student = clientTest.student();

                success ++;
//                System.out.println(student);
            } catch (SoyException e) {
                //
                e.printStackTrace();
                fail ++;
            }
        }

        System.out.println(success);
        System.out.println(fail);

    }

    private static InvokerConfig<ClientTest> makeConfig() {
        InvokerConfig<ClientTest> config = new DefaultInvokerConfig<ClientTest>();

        config.setCallType(BuiltIn.CallType.Sync);
        config.setServiceName("http://localhost:9999");
        config.setSerialize(BuiltIn.Serialize.JSON);
        config.setTimeout(100);
        config.setProto(BuiltIn.Proto.HTTP);
        config.setInterface(ClientTest.class);
        return config;
    }
}
