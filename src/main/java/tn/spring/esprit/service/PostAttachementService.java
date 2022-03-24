package tn.spring.esprit.service;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.spring.esprit.ServiceInterface.PostAttachementInterface;
import tn.spring.esprit.repository.PostAttachementRepository;


import java.net.MalformedURLException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class PostAttachementService implements PostAttachementInterface {

    @Autowired
    PostAttachementRepository postAttachementRepository;

    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        if (!Files.exists(root)){
            try {
                Files.createDirectory(root);
            } catch (IOException e) {
                throw new RuntimeException("Could not initialize folder for upload!");
            }
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
