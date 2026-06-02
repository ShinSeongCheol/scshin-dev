package kr.scshin.scshin_dev.blog.application.port.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostCreateRecordCommand;
import lombok.Builder;

import java.util.List;

@Builder
public record PostCreateCommand(
        @NotBlank(message = "제목은 필수입니다.")
        String title,
        String content,
        List<Long> categories,
        Long authorId
) {
        public static PostCreateCommand from(PostCreateRecordCommand postCreateRecordCommand) {
                return PostCreateCommand.builder()
                        .title(postCreateRecordCommand.title())
                        .content(postCreateRecordCommand.content())
                        .categories(postCreateRecordCommand.categories())
                        .authorId(postCreateRecordCommand.authorId())
                        .build();
        }
}
