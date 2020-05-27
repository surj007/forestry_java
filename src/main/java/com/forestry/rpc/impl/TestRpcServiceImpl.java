// 这个文件是自己写的
package com.forestry.rpc.impl;

import org.apache.thrift.TException;

import com.forestry.rpc.TestRpcService;

public class TestRpcServiceImpl implements TestRpcService.Iface {
    @Override
    public String console(String content) throws TException {
        System.out.println(content);
        return "console this: " + content;
    }
}
