package kr.scshin.scshin_dev.backoffice.application.port.out.dto.request;

public record PostUpdateRecordCommand(
        Long id,
        String title,
        String content
) {}
