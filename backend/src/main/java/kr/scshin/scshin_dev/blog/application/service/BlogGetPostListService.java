package kr.scshin.scshin_dev.blog.application.service;

import kr.scshin.scshin_dev.blog.application.port.in.BlogGetPostListUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.BlogPostListResponse;
import kr.scshin.scshin_dev.blog.application.port.out.BlogLoadPostListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogGetPostListService implements BlogGetPostListUseCase {

    private final BlogLoadPostListPort blogLoadPostListPort;

    @Override
    public List<BlogPostListResponse> getPostList() {
        return blogLoadPostListPort.loadPostList().stream().map(post -> new BlogPostListResponse(post.id(), post.title(), post.content(), post.authorId(), post.createdAt(), post.updatedAt())).toList();
    }
}
