package web_shop.api_web_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
//@Controller return html package
public class Hello {

    @GetMapping(" /hello")
    // @ResponseBody use with @controller to return methode sayHello not return html
    public String sayHello() {
        return "Hello, World!";
    }
}
