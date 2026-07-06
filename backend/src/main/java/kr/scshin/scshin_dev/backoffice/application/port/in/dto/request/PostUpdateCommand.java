package kr.scshin.scshin_dev.backoffice.application.port.in.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record PostUpdateCommand(
        Long id,
        String title,
        String content,
        List<Long> categories
) {}
