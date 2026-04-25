package kr.scshin.scshin_dev.backoffice.application.port.out.dto.request;

import java.time.LocalDateTime;

public record PostCreateRecordCommand(
    String title,
    String content,
    Long authorId
) {}
