package kr.scshin.scshin_dev.backoffice.adapter.in.web;

import jakarta.validation.Valid;
import kr.scshin.scshin_dev.auth.adapter.out.security.CustomUserDetails;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.PostCreateRequest;
import kr.scshin.scshin_dev.backoffice.application.port.in.BackofficeGetPostListUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.CreatePostUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostCreateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/backoffice")
@RequiredArgsConstructor
public class BackOfficeController {

    private final BackofficeGetPostListUseCase getPostListUseCase;
    private final CreatePostUseCase createPostUseCase;

    @GetMapping("")
    public String backOffice(Model model) {
        model.addAttribute("menu", "dashboard");
        return "backoffice/index";
    }

    @GetMapping("/login")
    public String login() {return "backoffice/login";}

    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("menu", "category");
        model.addAttribute("pageTitle", "카테고리");
        model.addAttribute("pageSubTitle", "블로그의 카테고리를 관리하세요");

        return "backoffice/views/category/category";
    }

    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("menu", "post");
        model.addAttribute("postList", getPostListUseCase.getPostList());
        return "backoffice/views/post/post";
    }

    @GetMapping("/post/new")
    public String postNew(Model model) {return "backoffice/views/post/newPost";}

    @PostMapping("/post/new")
    @ResponseBody
    public ResponseEntity<String> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long authorId = customUserDetails.getId();
        PostCreateCommand postCreateCommand = new PostCreateCommand(postCreateRequest.title(), postCreateRequest.content(), authorId);
        createPostUseCase.createPost(postCreateCommand);

        return ResponseEntity.ok("Success");
    }
}
