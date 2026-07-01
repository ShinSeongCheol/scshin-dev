package kr.scshin.scshin_dev.backoffice.adapter.in.web;

import jakarta.validation.Valid;
import kr.scshin.scshin_dev.auth.adapter.out.security.CustomUserDetails;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.CategoryCreateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.CategoryUpdateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.PostCreateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.PostUpdateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.response.CategoryResponse;
import kr.scshin.scshin_dev.backoffice.application.port.in.*;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.*;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.CategoryReadResponse;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.CategoryTreeReadResponse;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.PostReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/backoffice")
@RequiredArgsConstructor
public class BackOfficeController {

    private final CategoryCreateUseCase categoryCreateUseCase;
    private final CategoryReadUseCase categoryReadUseCase;
    private final CategoryUpdateUseCase categoryUpdateUseCase;

    private final CreatePostUseCase createPostUseCase;
    private final PostReadUseCase postReadUseCase;
    private final PostUpdateUseCase postUpdateUseCase;

    @GetMapping("")
    public String backOffice(Model model) {
        model.addAttribute("menu", "dashboard");
        return "backoffice/index";
    }

    @GetMapping("/categories")
    public List<CategoryTreeReadResponse> category() {
        List<CategoryTreeReadResponse> categoryTreeReadResponses = categoryReadUseCase.readTreeCategories(CategoryTreeReadQuery.builder().build());
        int totalCount = CategoryTreeReadResponse.countTotalCategories(categoryTreeReadResponses);

        log.info("categoryTreeReadResponses: {}", categoryTreeReadResponses.toString());
        log.info("totalCount: {}", totalCount);

        return categoryTreeReadResponses;
    }

    @GetMapping("/category/new")
    public String newCategory(Model model) {
        List<CategoryReadResponse> categories = categoryReadUseCase.readCategories(CategoryReadQuery.builder().build());
        model.addAttribute("categories", categories);

        return "backoffice/views/category/newCategory";
    }

    @PostMapping("/categories")
    public String createCategory(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
        CategoryCreateCommand categoryCreateCommand = CategoryCreateCommand.builder()
                .parentCategoryId(categoryCreateRequest.parentCategoryId())
                .categoryName(categoryCreateRequest.categoryName())
                .slug(categoryCreateRequest.slug())
                .description(categoryCreateRequest.description())
                .useYn(categoryCreateRequest.useYn())
                .build();

        log.info(categoryCreateCommand.toString());

        categoryCreateUseCase.createCategory(categoryCreateCommand);
        return "Success";
    }

    @GetMapping("/category/edit/{id}")
    public String newCategory(Model model, @PathVariable Long id) {
        List<CategoryReadResponse> categories = categoryReadUseCase.readCategories(CategoryReadQuery.builder().build());
        CategoryReadResponse category = categoryReadUseCase.readCategory(CategoryReadQuery.builder().id(id).build());
        model.addAttribute("categories", categories);
        model.addAttribute("savedCategory", category);

        return "backoffice/views/category/editCategory";
    }

    @PatchMapping("/category/edit/{id}")
    @ResponseBody
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        log.info("category edit id: {}", id);
        log.info("categoryUpdateRequest: {}", categoryUpdateRequest);

        categoryUpdateUseCase.updateCategory(CategoryUpdateCommand.builder()
                .id(id)
                .parentCategoryId(categoryUpdateRequest.parentCategoryId())
                .categoryName(categoryUpdateRequest.categoryName())
                .slug(categoryUpdateRequest.slug())
                .description(categoryUpdateRequest.description())
                .useYn(categoryUpdateRequest.useYn())
                .build()
        );

        return ResponseEntity.ok("Success");
    }


    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("menu", "post");
        model.addAttribute("postList", postReadUseCase.readPostList());
        return "backoffice/views/post/post";
    }

    @GetMapping("/post/new")
    public String postNew(Model model) {
        List<CategoryReadResponse> categories = categoryReadUseCase.readCategories(CategoryReadQuery.builder().build());
        List<CategoryResponse> categoryResponseList = categories.stream().map(category -> CategoryResponse.builder().id(category.id()).categoryName(category.categoryName()).build()).toList();
        model.addAttribute("categoryList",  categoryResponseList);
        return "backoffice/views/post/newPost";
    }

    @PostMapping("/post/new")
    @ResponseBody
    public ResponseEntity<String> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("post create request: {}", postCreateRequest);

        Long authorId = customUserDetails.getId();
        PostCreateCommand postCreateCommand = PostCreateCommand.from(postCreateRequest, authorId);
        createPostUseCase.createPost(postCreateCommand);

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/post/edit/{id}")
    public String postEdit(Model model, @PathVariable Long id) {
        PostReadQuery postReadQuery = new PostReadQuery(id);
        PostReadResponse postReadResponse = postReadUseCase.readPost(postReadQuery);
        model.addAttribute("post", postReadResponse);

        List<CategoryReadResponse> categories = categoryReadUseCase.readCategories(CategoryReadQuery.builder().build());
        List<CategoryResponse> categoryResponseList = categories.stream().map(category -> CategoryResponse.builder().id(category.id()).categoryName(category.categoryName()).build()).toList();
        model.addAttribute("categoryList",  categoryResponseList);

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
