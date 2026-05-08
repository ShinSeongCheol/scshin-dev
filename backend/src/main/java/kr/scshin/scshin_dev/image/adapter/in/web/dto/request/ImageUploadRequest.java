package kr.scshin.scshin_dev.image.adapter.in.web.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record ImageUploadRequest (MultipartFile image) {

}
