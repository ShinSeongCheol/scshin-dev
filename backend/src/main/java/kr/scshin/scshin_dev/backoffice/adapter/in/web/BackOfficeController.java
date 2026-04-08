package kr.scshin.scshin_dev.backoffice.adapter.in.web;

import kr.scshin.scshin_dev.backoffice.application.port.in.BackofficeGetPostListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BackOfficeController {

    private final BackofficeGetPostListUseCase getPostListUseCase;

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
        model.addAttribute("pageTitle", "카테고리");
        model.addAttribute("pageSubTitle", "블로그의 카테고리를 관리하세요");

        return "backoffice/views/category/category";
    }

    @GetMapping("/backoffice/post")
    public String post(Model model) {
        model.addAttribute("menu", "post");
        model.addAttribute("postList", getPostListUseCase.getPostList());
        return "backoffice/views/post/post";
    }

    @GetMapping("/backoffice/post/new")
    public String postNew(Model model) {return "backoffice/views/post/post_form";}
}
