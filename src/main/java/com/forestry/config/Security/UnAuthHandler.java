package com.forestry.config.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.forestry.dto.CommonResDto;

@Component
public class UnAuthHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(
        HttpServletRequest req,
        HttpServletResponse res,
        AuthenticationException e
    ) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        CommonResDto commonResDto = CommonResDto.error("请重新登陆");
        out.write(new ObjectMapper().writeValueAsString(commonResDto));
        out.flush();
        out.close();
    }
}
