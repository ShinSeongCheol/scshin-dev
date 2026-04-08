package kr.scshin.scshin_dev.blog.application.port.in;

import java.util.List;

public interface BlogGetPostListUseCase {
    List<BlogPostListResponse> getPostList();
}
