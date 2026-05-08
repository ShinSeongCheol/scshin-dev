package kr.scshin.scshin_dev.image.adapter.in.web;

import kr.scshin.scshin_dev.auth.adapter.out.security.CustomUserDetails;
import kr.scshin.scshin_dev.image.adapter.in.web.dto.request.ImageUploadRequest;
import kr.scshin.scshin_dev.image.adapter.in.web.dto.response.ImageUploadResponse;
import kr.scshin.scshin_dev.image.application.port.in.PostImageUploadUseCase;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageUploadRequest;
import kr.scshin.scshin_dev.image.application.port.in.dto.response.PostImageUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final PostImageUploadUseCase postImageUploadUseCase;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<ImageUploadResponse> upload(@ModelAttribute ImageUploadRequest imageUploadRequest, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        PostImageUploadRequest postImageUploadRequest = PostImageUploadRequest.builder()
                .multipartFile(imageUploadRequest.image())
                .userId(customUserDetails.getId())
                .build();

        PostImageUploadResponse postImageUploadResponse = postImageUploadUseCase.uploadImage(postImageUploadRequest);
        return ResponseEntity.ok(ImageUploadResponse.builder().filePath(postImageUploadResponse.filePath() + postImageUploadResponse.storedName()).build());
    }

}
