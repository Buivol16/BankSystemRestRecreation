package ua.denys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransferController {

    @GetMapping
    public String transferMoney(){
        return "";
    }

    @PostMapping
    public String doTransferMoney(){
        return "";
    }
}
