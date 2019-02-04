package com.forestry.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forestry.dto.CommonResDto;
import com.forestry.util.CommonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Configuration
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e) {
        try {
            res.setContentType("application/json;charset=UTF-8");
            PrintWriter out = res.getWriter();
            CommonResDto commonResDto = CommonResDto.error("网络错误，请稍后重试");
            out.write(new ObjectMapper().writeValueAsString(commonResDto));
            out.flush();
            out.close();
        }
        catch(Exception ex) {
            CommonUtil.Logger(this.getClass()).error(ex.toString());
        }

        return null;
    }
}
