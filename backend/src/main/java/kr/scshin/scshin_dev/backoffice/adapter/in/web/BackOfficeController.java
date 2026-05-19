package kr.scshin.scshin_dev.backoffice.adapter.in.web;

import jakarta.validation.Valid;
import kr.scshin.scshin_dev.auth.adapter.out.security.CustomUserDetails;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.CategoryCreateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.PostCreateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.PostUpdateRequest;
import kr.scshin.scshin_dev.backoffice.application.port.in.CategoryCreateUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.CreatePostUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.PostUpdateUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.CategoryCreateCommand;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostCreateCommand;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostUpdateCommand;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.PostReadResponse;
import kr.scshin.scshin_dev.backoffice.application.port.out.CategoryCreatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/backoffice")
@RequiredArgsConstructor
public class BackOfficeController {

    private final CategoryCreateUseCase categoryCreateUseCase;

    private final CreatePostUseCase createPostUseCase;
    private final PostReadUseCase postReadUseCase;
    private final PostUpdateUseCase postUpdateUseCase;

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

    @GetMapping("/category/new")
    public String newCategory() {
        return "backoffice/views/category/newCategory";
    }

    @PostMapping("/category/new")
    @ResponseBody
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
        CategoryCreateCommand categoryCreateCommand = CategoryCreateCommand.builder()
                .parentCategoryId(categoryCreateRequest.parentCategoryId())
                .categoryName(categoryCreateRequest.categoryName())
                .slug(categoryCreateRequest.slug())
                .description(categoryCreateRequest.description())
                .useYn(categoryCreateRequest.useYn())
                .build();

        log.info(categoryCreateCommand.toString());

        categoryCreateUseCase.createCategory(categoryCreateCommand);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("menu", "post");
        model.addAttribute("postList", postReadUseCase.readPostList());
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

    @GetMapping("/post/edit/{id}")
    public String postEdit(Model model, @PathVariable Long id) {
        PostReadQuery postReadQuery = new PostReadQuery(id);
        PostReadResponse postReadResponse = postReadUseCase.readPost(postReadQuery);
        model.addAttribute("post", postReadResponse);

        return "backoffice/views/post/editPost";
    }

    @PatchMapping("/post/edit/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest postUpdateRequest) {
        log.info("post edit id: {}", id);
        log.info("post edit request data: {}", postUpdateRequest.toString());

        postUpdateUseCase.updatePost(new PostUpdateCommand(id, postUpdateRequest.title(), postUpdateRequest.content()));

        return ResponseEntity.ok("Success");
    }
}
