package com.yhhy.FFMail.setting.common.config;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMailBasic.basic.common.JsonInterfaceTool;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 用来处理bean validation异常
   * 
   * @param ex
   * @return
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  public JSONObject resolveConstraintViolationException(ConstraintViolationException ex) {
    Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
    if (!CollectionUtils.isEmpty(constraintViolations)) {
      StringBuilder msgBuilder = new StringBuilder();
      for (ConstraintViolation constraintViolation : constraintViolations) {
        msgBuilder.append(constraintViolation.getMessage()).append(",");
      }
      String errorMessage = msgBuilder.toString();
      if (errorMessage.length() > 1) {
        errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
      }
      return JsonInterfaceTool.fail(errorMessage);
    }
    return JsonInterfaceTool.fail(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public JSONObject resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
    if (!CollectionUtils.isEmpty(objectErrors)) {
      StringBuilder msgBuilder = new StringBuilder();
      for (ObjectError objectError : objectErrors) {
        msgBuilder.append(objectError.getDefaultMessage()).append(",");
      }
      String errorMessage = msgBuilder.toString();
      if (errorMessage.length() > 1) {
        errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
      }
      return JsonInterfaceTool.fail(errorMessage);
    }
    return JsonInterfaceTool.fail(ex.getMessage());
  }
}