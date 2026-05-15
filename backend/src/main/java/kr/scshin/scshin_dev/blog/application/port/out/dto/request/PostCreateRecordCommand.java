package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

public record PostCreateRecordCommand(String title, String content, Long authorId) {
}
