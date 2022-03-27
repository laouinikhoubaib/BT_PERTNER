package tn.esprit.spring.service.csv;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.CSV;
import tn.esprit.spring.helper.CSVHelper;
import tn.esprit.spring.repository.CsvRepository;

@Service
public class CSVService {
	
	
  @Autowired
  CsvRepository repository;

  public void save(MultipartFile file) {
    try {
      List<CSV> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<CSV> tutorials = repository.findAll();

    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
    return in;
  }

  public List<CSV> getAllTutorials() {
    return repository.findAll();
  }
}