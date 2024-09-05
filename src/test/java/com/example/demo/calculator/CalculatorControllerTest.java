package com.example.demo.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class CalculatorControllerTest {
    //here we are mocking calculator service sine we are not interested to test calculator service just testing controller line of code
//    private CalculatorService calculatorService = Mockito.mock(CalculatorService.class); //way 1
//    private CalculatorController calculatorController = new CalculatorController(calculatorService); //way 1

    @MockBean
    private CalculatorService calculatorService; //way 2

    @Autowired
    private CalculatorController calculatorController;//it should be define as @Controller //way-2

    @Test
    public void testAddWhenTwoIntegerArgPassed(){
        //defualt value returns zero from mock object if you want to returns the value you want use when and then clause
        when(calculatorService.sum(anyInt(),anyInt())).thenReturn(11); //anyInt() do not care what is passed just matters return value
        int a=5;
        int b=6;
        int expected=11;
        int actual=calculatorController.add(a,b);
        Assertions.assertEquals(expected,actual);
    }

}