package kr.scshin.scshin_dev.backoffice.application.port.out.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record PostUpdateRecordCommand(
        Long id,
        String title,
        String content,
        List<Long> categories
) {}
