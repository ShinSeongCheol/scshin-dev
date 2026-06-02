package kr.scshin.scshin_dev.backoffice.application.port.in.dto.request;

import kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request.PostCreateRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record PostCreateCommand(
    String title,
    String content,
    List<Long> categories,
    Long authorId
) {
    public static PostCreateCommand from (PostCreateRequest postCreateRequest, Long authorId) {
        return PostCreateCommand.builder()
                .title(postCreateRequest.title())
                .content(postCreateRequest.content())
                .categories(postCreateRequest.categories())
                .authorId(authorId)
                .build();
    }
}
