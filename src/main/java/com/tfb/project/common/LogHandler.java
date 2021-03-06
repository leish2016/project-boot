package com.tfb.project.common;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author leish
 */
@Aspect
@Component
@Slf4j
public class LogHandler {
    @Pointcut("execution(* com.tfb.*.controller.*.*(..))")
    public void log() {
    }

    @Around("log()")
    public Object showLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String clazz = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        StringBuilder param = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (Object arg : joinPoint.getArgs()) {
                if(arg == null){
                    continue;
                }
                if (param.length() > 0) {
                    param.append(", ");
                }
                param.append(arg.getClass().getSimpleName());
                if (arg instanceof byte[]) {
                    arg = new String((byte[])arg);
                }
                param.append('=').append(arg.toString());
            }
        }
        Object result = new Object();
        String print = "";
        try {
            result = joinPoint.proceed();
            print = StrUtil.maxLength(result.toString(), 150);
        } catch (Throwable e) {
            throw e;
        } finally {
            log.info("# [RT={}ms] -> [{}.{}({}),result={}]", (System.currentTimeMillis() - start), clazz, method, param.toString(),print);
        }
        return result;
    }
}
