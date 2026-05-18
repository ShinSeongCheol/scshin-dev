package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

import lombok.Builder;

@Builder
public record PostCreateRecordCommand(String title, String content, Long authorId, Long views) {
}
