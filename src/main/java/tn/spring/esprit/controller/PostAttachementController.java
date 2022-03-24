package tn.spring.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.esprit.service.PostAttachementService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/PostAttachement")
public class PostAttachementController {
    private final Path upload_directory = Paths.get("uploads");

    @Autowired
    PostAttachementService postAttachementsService;

    @RequestMapping(value="/uploadAttachement", method = RequestMethod.POST)
    public String saveAttach(@RequestParam ("file") MultipartFile file) throws SQLIntegrityConstraintViolationException {
        try {
            postAttachementsService.init();
            postAttachementsService.save(file);
            return "file uploaded successfully: " + file.getOriginalFilename() + "!";
        } catch (Exception e) {
            return "Could not upload the file: " + file.getOriginalFilename() + "! " + e ;
        }
    }

    @RequestMapping(value="/loadAttachement/{filename}", method = RequestMethod.GET)
    public byte[] getFile(@PathVariable("filename") String filename) throws Exception {
        return Files.readAllBytes(Paths.get(upload_directory.toAbsolutePath()+filename));
    }


}
