package com.liudonglin.simple.rpc.consumer;

import com.liudonglin.simple.rpc.api.GreetingService;
import com.liudonglin.simple.rpc.core.common.ProxyFactory;

public class ConsumerAppStart {

    public static void main(String[] args) {
        GreetingService gs = ProxyFactory.getProxy(GreetingService.class);
        String result = gs.sayHi("gavin");
        System.out.println(result);
    }

}
