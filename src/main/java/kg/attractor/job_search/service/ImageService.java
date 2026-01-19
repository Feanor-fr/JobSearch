package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ImageDto;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public interface ImageService {

    @SneakyThrows
    default String saveUploadedFile(MultipartFile file, String subDir){
        String uuidFile = UUID.randomUUID().toString();
        String fileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get("data/" + subDir);
        Files.createDirectories(pathDir);
        Path filePath = Paths.get(pathDir + "/" + fileName);

        if (!Files.exists(filePath)) Files.createFile(filePath);

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }

        return fileName;
    }

//    default ResponseEntity<?> downloadFile(String filename, String subDir, MediaType mediaType){
//        try {
//            byte[] image = Files.readAllBytes(Paths.get("data/" + subDir + "/" + filename));
//
//            Resource resource = new ByteArrayResource(image);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment filename=\"" + filename + "\"")
//                    .contentLength(resource.contentLength())
//                    .contentType(mediaType)
//                    .body(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Image not found");
//        }
//    }

    default ResponseEntity<?> downloadFile(String filename, String subDir){
        try {
            Path path = Paths.get("data/" + subDir + "/" + filename);
            byte[] image = Files.readAllBytes(path);

            String contentType = Files.probeContentType(path);
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new RuntimeException("Можно загружать только изображения!");
            }

            Resource resource = new ByteArrayResource(image);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .contentLength(resource.contentLength())
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }


    ResponseEntity<?> getById(String filename);

    void create(ImageDto imageDto);
}
