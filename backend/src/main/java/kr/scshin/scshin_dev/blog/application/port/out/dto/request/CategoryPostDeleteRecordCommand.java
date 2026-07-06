package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

public record CategoryPostDeleteRecordCommand(
        Long postId,
        Long categoryId
) {
}
