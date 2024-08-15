package com.example.demo.controlleradvice;

import com.example.demo.dtos.ErrorDto;
import com.example.demo.exceptions.ProductNotFoundExcepton;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExpetionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerException(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("FAILED");
        errorDto.setMessage("Something went wrong from Global Expetion Handler");
        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundExcepton.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundExcepton productNotFoundExcepton){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("FAILED");
        errorDto.setMessage(productNotFoundExcepton.getMessage());
        // Here ResponseEntity is used to send our own status code and messge to FE
        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto,HttpStatusCode.valueOf(404));
        return responseEntity;
    }
}
