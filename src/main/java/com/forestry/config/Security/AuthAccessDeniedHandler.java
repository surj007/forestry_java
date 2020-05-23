package com.forestry.config.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forestry.dto.CommonResDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
        HttpServletRequest req,
        HttpServletResponse res,
        AccessDeniedException e
    ) throws IOException {
        res.setStatus(403);
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        CommonResDto commonResDto = CommonResDto.error("当前角色不能访问此资源", e);
        out.write(new ObjectMapper().writeValueAsString(commonResDto));
        out.flush();
        out.close();
    }
}
