package com.cpcl.product.api;

import com.cpcl.global.dto.ErrorResponse;
import com.cpcl.member.exception.MemberNotFoundException;
import com.cpcl.product.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMemberNotFoundException(MemberNotFoundException e) {
        log.error("handleProductNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        return response;
    }

}
