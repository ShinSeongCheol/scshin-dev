package kr.scshin.scshin_dev.image.application.port.in.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record PostImageUpdateRequest(Long postId, List<String> fileNames) {
}
