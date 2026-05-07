package kr.scshin.scshin_dev.blog.adapter.in.web;

import kr.scshin.scshin_dev.blog.adapter.in.web.dto.response.PostResponse;
import kr.scshin.scshin_dev.blog.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import kr.scshin.scshin_dev.common.service.CommonMarkParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final PostReadUseCase postReadUseCase;
    private final CommonMarkParserService commonMarkParserService;

    @GetMapping("/blog")
    public String blog(Model model) {
        List<PostReadResponse> readPostList = postReadUseCase.readPostList();
        List<PostResponse> postResponseList = readPostList.stream().map(post -> PostResponse.builder()
                .title(post.title())
                .content(commonMarkParserService.renderAsSummary(post.content()))
                .createdAt(post.createdAt())
                .build()).toList();
        model.addAttribute("postResponseList", postResponseList);
        return "blog/index";
    }
}
