package tn.esprit.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.spring.entities.CSV;
import tn.esprit.spring.helper.CSVHelper;
import tn.esprit.spring.repository.CsvRepository;
import tn.esprit.spring.service.csv.CSVService;
import tn.esprit.spring.service.csv.ResponseMessage;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin("http://localhost:8082")
@Controller
@PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
@RequestMapping("/api/csv")

public class CSVController {
	
	

  @Autowired
  CSVService fileService;
  
  @Autowired
	CsvRepository csvRepository;
  
  
  @PostMapping("/upload")
 // @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
  public ResponseEntity<ResponseMessage> uploadFilecsv(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/csv/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
  }
  
  @GetMapping("/list")
  @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
  public ResponseEntity<List<CSV>> getAllTutorials() {
    try {
      List<CSV> tutorials = fileService.getAllTutorials();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }

  @GetMapping("/published")
  @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
	public ResponseEntity<List<CSV>> findByPublished() {
		try {
			List<CSV> tutorials = csvRepository.findByPublished(true);
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
	public ResponseEntity<CSV> getCsvById(@PathVariable("id") long id) {
		Optional<CSV> tutorialData = csvRepository.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
 
  @PutMapping("/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
	public ResponseEntity<CSV> updateCsv(@PathVariable("id") long id, @RequestBody CSV csv) {
		Optional<CSV> tutorialData = csvRepository.findById(id);
		if (tutorialData.isPresent()) {
			CSV _tutorial = tutorialData.get();
			_tutorial.setTitle(csv.getTitle());
			_tutorial.setDescription(csv.getDescription());
			_tutorial.setPublished(csv.isPublished());
			return new ResponseEntity<>(csvRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCsv(@PathVariable("id") long id) {
		try {
			csvRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}