package com.evoting.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    //@ExceptionHandler({CustomBad})
    public String handlerCustomBadRequestException(){

    }
}
