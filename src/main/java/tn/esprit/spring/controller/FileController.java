package tn.esprit.spring.controller;




import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.spring.entities.File;
import tn.esprit.spring.service.files.FilesStorageService;
import tn.esprit.spring.service.files.ResponseFile;
import tn.esprit.spring.service.files.ResponseMessage;



@Controller
@CrossOrigin("http://localhost:8082")
@RequestMapping("/api/file")
public class FileController {
	
	
  @Autowired
  private FilesStorageService storageService;
  
  
  
  @PostMapping("/upload/{idfile}/{id}")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.store(file);
      //storageService.affectToUser(idfile,id);
      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));    
    }
    
  }
  @PostMapping("/affectatFileToUser/{idfile}/{id}")
  @ResponseBody
  public void affectatFileToUser(@PathVariable("idfile") int idfile,@PathVariable("id")int id) throws IOException{
	  storageService.affectatFileToUser(idfile, id);
  }
  
  
  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/files/")
          .toUriString();
      return new ResponseFile(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(files);
  }
  @GetMapping("/files/{idfile}")
  public ResponseEntity<byte[]> getFile(@PathVariable int idfile) {
    File fileDB = storageService.getFile(idfile);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }
}