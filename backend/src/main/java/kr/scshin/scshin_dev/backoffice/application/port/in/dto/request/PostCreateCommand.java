package kr.scshin.scshin_dev.backoffice.application.port.in.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PostCreateCommand(
    @NotBlank(message = "제목은 필수입니다.")
    String title,
    String content,
    Long authorId
) { }
