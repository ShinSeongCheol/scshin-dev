package kr.scshin.scshin_dev.image.application.port.in;

import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageUpdateRequest;

public interface PostImageUpdateUseCase {
    void updateImage(PostImageUpdateRequest postImageUpdateRequest);
}
