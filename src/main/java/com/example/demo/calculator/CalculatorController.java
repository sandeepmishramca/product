package com.example.demo.calculator;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CalculatorController {
    private CalculatorService calculatorService;
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }
    public Integer add(int a, int b) {
        System.out.println("from controller add method");
        System.out.println("some logic from controller add method");
        int result = calculatorService.sum(a,b);
        return result;
    }
}
