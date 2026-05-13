package kr.scshin.scshin_dev.image.application.port.in;

import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageReadRequest;
import kr.scshin.scshin_dev.image.application.port.in.dto.response.PostImageReadResponse;

import java.util.List;

public interface PostImageReadUseCase {
    List<List<PostImageReadResponse>> readPostImages(PostImageReadRequest postImageReadRequest);
}
