package kr.scshin.scshin_dev.backoffice.application.port.in.dto.request;

public record PostCreateCommand(
    String title,
    String content,
    Long authorId
) {}
