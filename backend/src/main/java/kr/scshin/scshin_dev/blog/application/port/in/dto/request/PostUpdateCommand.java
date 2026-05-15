package kr.scshin.scshin_dev.blog.application.port.in.dto.request;

public record PostUpdateCommand(Long id, String title, String content) {
}
