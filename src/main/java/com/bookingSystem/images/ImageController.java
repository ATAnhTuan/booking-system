package com.bookingSystem.images;

import com.bookingSystem.exception.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@Api(tags = "Image Management")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{guid}")
    public ResponseEntity<ApiResponse<ImageResponseDTO>> getImage(@PathVariable UUID guid) {
        return ResponseEntity.ok(ApiResponse.success(imageService.getByGuid(guid), "Image found successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ImageResponseDTO>>> getImages() {
        return ResponseEntity.ok(ApiResponse.success(imageService.getAllImages(), "Images found successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ImageResponseDTO>> createImage(@Valid @RequestBody ImageRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(imageService.createImage(request), "Create image success"));
    }

    @PutMapping("/{guid}")
    public ResponseEntity<ApiResponse<ImageResponseDTO>> updateImage(
            @PathVariable UUID guid,
            @Valid @RequestBody ImageRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success(imageService.updateImage(guid, request), "Image updated success"));
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity<Void> deleteImage(@PathVariable UUID guid) {
        imageService.deleteImage(guid);
        return ResponseEntity.noContent().build();
    }
}
