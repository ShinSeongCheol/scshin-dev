package kr.scshin.scshin_dev.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Post{
    private final Long id;
    private String title;
    private String content;
    private final Long authorId;
    private final LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public void update(String title, String content) {
       this.title = title;
       this.content = content;
       this.updateAt = LocalDateTime.now();
    }
}
