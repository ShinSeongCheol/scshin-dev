package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public record PostImageReadRecordQuery(@Singular List<Long> postIds) {
}
