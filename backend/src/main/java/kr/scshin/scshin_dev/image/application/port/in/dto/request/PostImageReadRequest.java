package kr.scshin.scshin_dev.image.application.port.in.dto.request;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public record PostImageReadRequest(@Singular List<Long> postIds) {
}
