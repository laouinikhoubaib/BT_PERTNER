package tn.esprit.spring.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.io.*;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadFile {
	@Value("${app.upload.dir:${user.home}}")
    public String uploadDirectory;

	private String uploadFile(MultipartFile uploadFile) {
	    String url = "";
	    try {
	     
	     url= Paths.get(uploadDirectory, uploadFile.getOriginalFilename()).toString();
	
	    } catch (Exception e) {
	      url = "Could not upload the file: " + uploadFile.getOriginalFilename() + "!";
	     
	    }
	    return url;
	  }
	/*public  void saveFile(
            MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }  
		//file.transferTo(new File("D:\\UploadFiles"+file.getOriginalFilename()));//with postman
    }*/
		
}
