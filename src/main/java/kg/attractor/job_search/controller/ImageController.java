package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.ImageDto;
import kg.attractor.job_search.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{filename}")
    public ResponseEntity<?> getImage(@PathVariable String filename){
        return imageService.getById(filename);
    }

    @PostMapping
    public HttpStatus create(ImageDto imageDto){
        imageService.create(imageDto);
        return HttpStatus.CREATED;
    }
}
