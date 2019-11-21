package com.liudonglin.simple.rpc.core.common;

public class RpcInvocation {

    private String interfaceName;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] arguments;

    public RpcInvocation(String interfaceName,String methodName,Class<?>[] parameterTypes,Object[] arguments) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.arguments = arguments;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }
}
