package kr.scshin.scshin_dev.backoffice.domain;

import java.time.LocalDateTime;

public record Post (
    Long id,
    String title,
    String content,
    Long authorId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
){

}
