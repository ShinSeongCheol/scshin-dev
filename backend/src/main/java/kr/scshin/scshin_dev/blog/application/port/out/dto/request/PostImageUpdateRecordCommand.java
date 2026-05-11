package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

import java.util.List;

public record PostImageUpdateRecordCommand(Long postId, List<String> fileNames) {
}
