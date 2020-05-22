package com.forestry.aop;

import com.forestry.dto.CommonResDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

// 这个aop是用来配合bean中@NotBlank这类注解和controller中@Valid进行请求参数检验
@Component
@Aspect
public class BindingResultAop {
    @Pointcut("execution(* com.forestry.controller.*.*(..))")
    public void aopMethod() {}

    @Around("aopMethod()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        BindingResult bindingResult = null;

        // proceedingJoinPoint.getArgs()为拦截到的方法的参数列表
        for (Object arg : proceedingJoinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                bindingResult = (BindingResult)arg;
            }
        }

        if (bindingResult != null) {
            if (bindingResult.hasErrors()) {
                return CommonResDto.error(bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
            }
        }

        return proceedingJoinPoint.proceed();
    }
}
