package kr.scshin.scshin_dev.image.application.port.in;

import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageUploadRequest;
import kr.scshin.scshin_dev.image.application.port.in.dto.response.PostImageUploadResponse;

public interface PostImageUploadUseCase {
    PostImageUploadResponse uploadImage(PostImageUploadRequest postImageUploadRequest);
}
