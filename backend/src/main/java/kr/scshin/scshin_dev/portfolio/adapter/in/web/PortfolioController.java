package kr.scshin.scshin_dev.portfolio.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioController {
    @GetMapping("/portfolio")
    public String portfolio() {
        return "portfolio/index";
    }
}
