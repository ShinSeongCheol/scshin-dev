package kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PostCreateRequest(
        @NotBlank(message = "제목은 필수입니다.")
        String title,
        String content,
        List<Long> categories
) {}
