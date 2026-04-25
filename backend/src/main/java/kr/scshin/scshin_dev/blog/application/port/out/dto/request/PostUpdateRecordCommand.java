package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

public record PostUpdateRecordCommand(Long id, String title, String content, Long authorId) {
}
