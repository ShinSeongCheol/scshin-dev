package kr.scshin.scshin_dev.backoffice.adapter.in.web;

import jakarta.validation.Valid;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.CategoryCreateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.CategoryUpdateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.PostCreateRequest;
import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.PostUpdateRequest;
import kr.scshin.scshin_dev.backoffice.application.port.in.*;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.*;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.CategoryTreeReadResponse;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.PostReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @PatchMapping("/categories")
    public void updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        log.info("categoryUpdateRequest: {}", categoryUpdateRequest);

        categoryUpdateUseCase.updateCategory(CategoryUpdateCommand.builder()
                .id(categoryUpdateRequest.categoryId())
                .parentCategoryId(categoryUpdateRequest.parentCategoryId())
                .categoryName(categoryUpdateRequest.categoryName())
                .slug(categoryUpdateRequest.slug())
                .description(categoryUpdateRequest.description())
                .useYn(categoryUpdateRequest.useYn())
                .build()
        );
    }

    @PostMapping("/posts/new")
    public ResponseEntity<String> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest, @AuthenticationPrincipal Jwt jwt) {
        log.info("post create request: {}", postCreateRequest);

        Long authorId = jwt.getClaim("userId");
        PostCreateCommand postCreateCommand = PostCreateCommand.from(postCreateRequest, authorId);
        createPostUseCase.createPost(postCreateCommand);

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/posts/edit/{id}")
    public PostReadResponse postEdit(@PathVariable Long id) {
        log.info("get edit post info: {}", id);

        PostReadQuery postReadQuery = new PostReadQuery(id);
        return postReadUseCase.readPost(postReadQuery);
    }

    @PatchMapping("/posts/edit/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest postUpdateRequest) {
        log.info("post edit id: {}", id);
        log.info("post edit request data: {}", postUpdateRequest.toString());

        postUpdateUseCase.updatePost(new PostUpdateCommand(id, postUpdateRequest.title(), postUpdateRequest.content()));

        return ResponseEntity.ok("Success");
    }
}
