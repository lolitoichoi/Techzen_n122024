package com.techzen.academy_n1224;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String greeting (@RequestParam(defaultValue = "World")String name,
                            @RequestParam(defaultValue = "Hoà Tiến")String address) {
//        if(name == null ) {
//            name = "World";
//        }
//        if(adress == null) {
//            adress = "Hoà Tiến";
//        }
        return "Hello" + " " + name + " - " + address;
    }
}
