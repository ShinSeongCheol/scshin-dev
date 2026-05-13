package kr.scshin.scshin_dev.image.application.port.out.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record PostImageReadRecordQuery(List<Long> postIds) {
}
