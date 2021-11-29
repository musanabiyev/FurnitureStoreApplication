package com.company.util;

import com.company.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUploadUtil {

    public void saveFile(String path, MultipartFile multipartFile) {
        Path uploadPath = Paths.get(path);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, uploadPath.resolve(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Path load(String filename, String upload) {
        Path uploadPath = Paths.get(upload);
        return uploadPath.resolve(filename);
    }

    public Resource loadAsResource(String filename, String upload) {
        try {
            Path file = load(filename, upload);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
}
