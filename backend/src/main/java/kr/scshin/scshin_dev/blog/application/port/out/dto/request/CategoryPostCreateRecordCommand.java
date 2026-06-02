package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

import lombok.Builder;

@Builder
public record CategoryPostCreateRecordCommand(
        Long categoryId,
        Long postId
) {
    public static CategoryPostCreateRecordCommand from() {
        return CategoryPostCreateRecordCommand.builder().build();
    }
}
