package kr.scshin.scshin_dev.image.application.port.in.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record PostImageDeleteRequest(Long postId, List<String> fileNames) {
}
