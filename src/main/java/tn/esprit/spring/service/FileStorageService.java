package tn.esprit.spring.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Eventparticipants;
import tn.esprit.spring.entities.FileDB;
import tn.esprit.spring.repository.EventStatsRepository;
import tn.esprit.spring.repository.FileDBRepository;

@Service
public class FileStorageService {
  @Autowired
  private FileDBRepository fileDBRepository;
  public FileDB store(MultipartFile file , String idnews) throws IOException {
    String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(fileName,  idnews ,file.getContentType(), file.getBytes());
    return fileDBRepository.save(FileDB);
  }
  public FileDB getFile(int id) {
    return fileDBRepository.findById(id).get();
  }
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }
  @Autowired
	FileDBRepository er ;
  
  public List<FileDB> showdoc (String id){
		
	   
	   return er.showdoc(id).stream().sorted(Comparator.comparing(FileDB::getIdnews))
				.collect(Collectors.toList());
   
}
}
