package com.forestry.rpc.impl;

import com.forestry.rpc.TestRpcService;
import org.apache.thrift.TException;

public class TestRpcServiceImpl implements TestRpcService.Iface {
    @Override
    public String console(String content) throws TException {
        System.out.println(content);
        return "console this: " + content;
    }
}
