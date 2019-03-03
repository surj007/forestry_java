package com.forestry.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forestry.dto.CommonResDto;
import com.forestry.util.CommonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Configuration
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e) {
        //HandlerExceptionResolver会先捕获AccessDeniedException错误，所以抛出，让后面的AccessDeniedHandler捕获
        if(e instanceof AccessDeniedException) {
            throw new AccessDeniedException("不允许访问", e);
        }

        ModelAndView mv = new ModelAndView();

        try {
            res.setStatus(500);
            res.setContentType("application/json;charset=UTF-8");
            PrintWriter out = res.getWriter();
            CommonResDto commonResDto = CommonResDto.error("global err", e);
            System.out.println(e);
            out.write(new ObjectMapper().writeValueAsString(commonResDto));
            out.flush();
            out.close();
        }
        catch(Exception ex) {
            CommonUtil.Logger(this.getClass()).error("global ex: ", ex);
        }

        return mv;
    }
}
