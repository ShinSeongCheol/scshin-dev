package kr.scshin.scshin_dev.blog.application.port.in;

import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadDetailResponse;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;

import java.util.List;

public interface PostReadUseCase {
    List<PostReadResponse> readPostList();
    PostReadDetailResponse readPost(PostReadQuery postReadQuery);
    PostReadResponse readPostAsHtml(PostReadQuery postReadQuery);
}
