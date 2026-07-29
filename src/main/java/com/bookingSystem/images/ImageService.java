package com.bookingSystem.images;

import com.bookingSystem.exception.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ImageService {
    private final ImageDAO imageDAO;
    private final ImageMapper imageMapper;

    public ImageService(ImageDAO imageDAO, ImageMapper imageMapper) {
        this.imageDAO = imageDAO;
        this.imageMapper = imageMapper;
    }

    @Transactional
    public ImageResponseDTO getByGuid(UUID guid) {
        return imageMapper.toResponseDTO(findImageByGuid(guid));
    }

    @Transactional
    public List<ImageResponseDTO> getAllImages() {
        return imageDAO.findAll()
                .stream()
                .map(imageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ImageResponseDTO createImage(ImageRequestDTO request) {
        validateOwner(request.getHotelGuid(), request.getRoomGuid());
        Images image = imageMapper.toEntity(request);
        return imageMapper.toResponseDTO(imageDAO.save(image));
    }

    @Transactional
    public ImageResponseDTO updateImage(UUID guid, ImageRequestDTO request) {
        validateOwner(request.getHotelGuid(), request.getRoomGuid());
        Images image = findImageByGuid(guid);
        imageMapper.updateEntityFromDTO(request, image);
        return imageMapper.toResponseDTO(imageDAO.update(image));
    }

    @Transactional
    public void deleteImage(UUID guid) {
        imageDAO.delete(findImageByGuid(guid));
    }

    private Images findImageByGuid(UUID guid) {
        return imageDAO.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found: " + guid));
    }

    private void validateOwner(UUID hotelGuid, UUID roomGuid) {
        if (hotelGuid == null && roomGuid == null) {
            throw new IllegalArgumentException("Image must belong to a hotel or a room");
        }
    }
}
