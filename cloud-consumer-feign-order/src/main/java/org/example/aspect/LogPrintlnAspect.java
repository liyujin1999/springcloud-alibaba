package org.example.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogPrintlnAspect {
    /**
     * 配置织入点
     */
    @Pointcut("execution(* org.example.controller..*(..))")
    public void allMethod() {
    }

    @Around("allMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] classNameArray = method.getDeclaringClass().getName().split("\\.");
        String methodName = classNameArray[classNameArray.length - 1] + "." + method.getName();

        String params = this.buildParamsDefault(joinPoint);
        long startTime = System.currentTimeMillis();
        Object result = null;
        try{
            log.info("[ {} ] requestParam: {}", methodName, params);
            result = joinPoint.proceed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            log.info("[ {} ] runTime: {} ms, result: {}", methodName, System.currentTimeMillis() - startTime, result);
        }

        return result;
    }

    private String buildParamsDefault(ProceedingJoinPoint joinPoint) {
        StringBuilder params = new StringBuilder(" [");

        for(int i = 0; i < joinPoint.getArgs().length; ++i) {
            Object obj = joinPoint.getArgs()[i];
            if (null != obj) {
                if (obj instanceof HttpServletRequest || obj instanceof HttpServletResponse) {
                    continue;
                }

                String str = obj.toString();
                if (obj.getClass() != String.class) {
                    str = ToStringBuilder.reflectionToString(obj, ToStringStyle.JSON_STYLE);
                }

                if (i != joinPoint.getArgs().length - 1) {
                    params.append(str).append(",");
                } else {
                    params.append(str).append(" ]");
                }
            }

            if (params.length() == 1) {
                params.append("]");
            }
        }

        return params.toString();
    }
}
