package com.forestry.config.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forestry.dto.CommonResDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest req,
                                HttpServletResponse res,
                                Authentication e) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        CommonResDto commonResDto = CommonResDto.ok("logout success");
        out.write(new ObjectMapper().writeValueAsString(commonResDto));
        out.flush();
        out.close();
    }
}
