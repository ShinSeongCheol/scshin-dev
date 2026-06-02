package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryPostId implements Serializable {
    private Long categoryId;
    private Long postId;
}
