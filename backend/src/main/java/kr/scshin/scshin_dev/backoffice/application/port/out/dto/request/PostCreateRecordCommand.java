package kr.scshin.scshin_dev.backoffice.application.port.out.dto.request;

import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostCreateCommand;
import lombok.Builder;

import java.util.List;

@Builder
public record PostCreateRecordCommand(
    String title,
    String content,
    List<Long> categories,
    Long authorId
) {
    public static PostCreateRecordCommand from(PostCreateCommand postCreateCommand) {
        return PostCreateRecordCommand.builder()
                .title(postCreateCommand.title())
                .content(postCreateCommand.content())
                .categories(postCreateCommand.categories())
                .authorId(postCreateCommand.authorId())
                .build();
    }
}
