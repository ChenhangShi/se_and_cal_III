package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.Enum.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/success")
    public String success(){
        return ResponseMessage.Success.toString();
    }

    @PostMapping("/failure")
    public String failure(){
        return ResponseMessage.Forbidden.toString();
    }
}
