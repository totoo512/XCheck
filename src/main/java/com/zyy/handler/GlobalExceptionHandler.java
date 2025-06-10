package com.zyy.handler;

import com.zyy.constant.MessageConstant;
import com.zyy.exception.BaseException;
import com.zyy.exception.DeletionNotAllowedException;
import com.zyy.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获数据库唯一键重复异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(DuplicateKeyException ex) {
        String message = ex.getMessage();
        if (message.contains("username")) {
            log.error(MessageConstant.ACCOUNT_ALREADY_EXISTS);
            return Result.error(MessageConstant.ACCOUNT_ALREADY_EXISTS);
        }
        log.error(MessageConstant.ALREADY_EXISTS);
        return Result.error(MessageConstant.ALREADY_EXISTS);
    }

    /**
     * 捕获不可删除异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(DeletionNotAllowedException ex) {
        String message = ex.getMessage();
        log.error("不可删除：{}", message);
        return Result.error(message);
    }

}
