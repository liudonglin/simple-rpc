package com.liudonglin.simple.rpc.provider.service;
import com.liudonglin.simple.rpc.api.GreetingService;

public class GreetingServiceImpl implements GreetingService {

    public String sayHi(String name) {
        return "this is simple rpc first callback name is :" + name;
    }

}
