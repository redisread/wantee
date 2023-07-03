package com.jiahongw.wantee.common.aop;

import com.jiahongw.wantee.common.enums.BizExceptionCodeEnum;
import com.jiahongw.wantee.dto.WebBaseResponse;
import com.jiahongw.wantee.exception.BizException;
import com.jiahongw.wantee.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Redisread
 * @date 2023/5/18
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(* com.jiahongw.wantee.controller.*.*(..))")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object aroundControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class returnType = signature.getReturnType();
        if (!(WebBaseResponse.class).isAssignableFrom(returnType)) {
            throw new BizException("Controller接口必须返回WebBaseResponse");
        }
        String methodName = getMethodName(joinPoint);
        Object response;
        try {
            log.info("WebController Call={} >>> Request:{}", methodName,
                JsonUtils.toJson(joinPoint.getArgs()));
            response = joinPoint.proceed();
            log.info("WebController Call={} >>> Response:{}", methodName,
                response);
        } catch (Exception e) {
            log.error("WebController Call Error={},error:", methodName, e);
            return WebBaseResponse.error(BizExceptionCodeEnum.FAIL.getCode(), e.getMessage());
        }
        return response;
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        return String.format("%s.%s", className, methodName);
    }
}
