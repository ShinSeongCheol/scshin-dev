package kr.scshin.scshin_dev.backoffice.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackOfficeController {

    @GetMapping("/backoffice")
    public String backOffice(Model model) {
        model.addAttribute("menu", "dashboard");
        return "backoffice/index";
    }

    @GetMapping("/backoffice/login")
    public String login() {return "backoffice/login";}

    @GetMapping("/backoffice/category")
    public String category(Model model) {
        model.addAttribute("menu", "category");
        return "backoffice/views/category/category";
    }

    @GetMapping("/backoffice/post")
    public String post(Model model) {
        model.addAttribute("menu", "post");
        return "backoffice/views/post/post";
    }
}
