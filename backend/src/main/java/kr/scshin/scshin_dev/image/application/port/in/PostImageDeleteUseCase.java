package kr.scshin.scshin_dev.image.application.port.in;

import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageDeleteRequest;

public interface PostImageDeleteUseCase {
    void deletePostImage(PostImageDeleteRequest postImageDeleteRequest);
}
