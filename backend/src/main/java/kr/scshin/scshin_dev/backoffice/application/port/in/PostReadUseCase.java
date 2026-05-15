package kr.scshin.scshin_dev.backoffice.application.port.in;

import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.PostReadResponse;

import java.util.List;

public interface PostReadUseCase {
    List<PostReadResponse> readPostList();
    PostReadResponse readPost(PostReadQuery postReadQuery);
}
