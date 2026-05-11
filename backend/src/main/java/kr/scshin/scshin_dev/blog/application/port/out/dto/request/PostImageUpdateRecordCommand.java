package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record PostImageUpdateRecordCommand(Long postId, List<String> fileNames) {
}
