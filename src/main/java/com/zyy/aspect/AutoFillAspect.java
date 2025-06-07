package com.zyy.aspect;

import com.zyy.annotation.AutoFill;
import com.zyy.constant.AutoFillConstant;
import com.zyy.context.BaseContext;
import com.zyy.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.zyy.mapper.*.*(..)) && @annotation(com.zyy.annotation.AutoFill)")
    public void autoFillPointcut() {}

    /**
     * 前置通知
     */
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始公共字段填充...");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();  // 获得方法签名
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);  // 获得方法上的注解对象
        OperationType operationType = autoFill.value();  // 获得数据库操作类型

        // 获取当前被拦截的方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            log.info("当前拦截方法无参数，无法自动填充...");
            return;
        }

        // 要赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Integer currentId = BaseContext.getCurrentId();

        // 约定第一个参数为实体类
        Object entity = args[0];

        // 插入操作需要填充4个字段
        try {
            if (operationType == OperationType.INSERT) {
                entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class).invoke(entity, now);
//                entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class).invoke(entity, currentId);
                entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, now);
//                entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class).invoke(entity, currentId);
            } else if (operationType == OperationType.UPDATE) {
                entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, now);
//                entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class).invoke(entity, currentId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
