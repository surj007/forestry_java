package com.zhengqing.modules.common.utils;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
    private final byte[] body;

    public MultiReadHttpServletRequest(HttpServletRequest req) throws IOException {
        super(req);
        body = getBodyString(req).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    private String getBodyString(ServletRequest req) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            inputStream = req.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }
    
    public String getBodyJsonStrByJson(ServletRequest req) {
        StringBuffer stringBuilder = new StringBuffer();
        String line = "";

        try {
            BufferedReader bufferedReader = req.getReader();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}