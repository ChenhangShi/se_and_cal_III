package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.Enum.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/success")
    public ResponseMessage success(){
        return ResponseMessage.Success;
    }

    @PostMapping("/failure")
    public ResponseMessage failure(){
        return ResponseMessage.Forbidden;
    }
}
