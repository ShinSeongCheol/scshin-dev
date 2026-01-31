package kr.scshin.scshin_dev.backoffice.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackOfficeController {

    @GetMapping("/backoffice")
    public String backOffice() {
        return "backoffice/index";
    }

    @GetMapping("/backoffice/login")
    public String login() {return "backoffice/login";}

}
