package com.techzen.academy_n1224;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcualtorController {
    @GetMapping("/calculator")
    public ResponseEntity<String> calculator(
            @RequestParam(value = "firstNumber", defaultValue = "") String fisrtNumberStr,
            @RequestParam(value = "secondNumber", defaultValue = "") String secondNumberStr,
            @RequestParam(value = "operator", defaultValue = "") String operator) {

        if (fisrtNumberStr.isEmpty()) {
            return ResponseEntity.badRequest().body("First number cannot be empty");
        } else if (secondNumberStr.isEmpty()) {
            return ResponseEntity.badRequest().body("Second number cannot be empty");
        } else if (!isDouble(fisrtNumberStr)) {
            return ResponseEntity.badRequest().body("First number must be numeric");
        } else if (!isDouble(secondNumberStr)) {
            return ResponseEntity.badRequest().body("Second number must be numeric");
        }

        double firstNumber = Double.parseDouble(fisrtNumberStr);
        double secondNumber = Double.parseDouble(secondNumberStr);
        double result;

        switch (operator) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> {
                if (secondNumber == 0) {
                    return ResponseEntity.badRequest().body("Division by is not allowed");
                }
                result = firstNumber / secondNumber;
            }
            default -> {
                return ResponseEntity.badRequest().body("Invalid operator");
            }
        }
        return ResponseEntity.ok("Result: " + result);
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
