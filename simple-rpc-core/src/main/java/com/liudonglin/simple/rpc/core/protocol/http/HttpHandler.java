package com.liudonglin.simple.rpc.core.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.liudonglin.simple.rpc.core.common.RpcInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpHandler {

    public void handle(HttpServletRequest req, HttpServletResponse res) {


        if (!req.getMethod().equalsIgnoreCase("POST")) {
            res.setStatus(500);
        } else {
            try {
                RpcInvocation invocation = JSONObject.parseObject(req.getInputStream(),RpcInvocation.class);
                String interfaceName = invocation.getInterfaceName();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
