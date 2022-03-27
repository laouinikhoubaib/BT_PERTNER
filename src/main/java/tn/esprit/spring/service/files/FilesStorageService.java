package tn.esprit.spring.service.files;

import java.io.IOException;
import java.util.stream.Stream;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.File;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.FileDBRepository;
import tn.esprit.spring.repository.UserRepository;



@Service
public class FilesStorageService {
	
	
	
	@Autowired
    UserRepository ur;
	
	@Autowired
	FileDBRepository fr;
	
	
	public  File store(MultipartFile file) throws IOException {
		
		String name = StringUtils.cleanPath(file.getOriginalFilename());
        File files = new File(name, file.getContentType(), file.getBytes());

        return fr.save(files);
      }
	
	  public File getFile(int id) {
	    return fr.findById(id).get();
	  }
	  
	  public Stream<File> getAllFiles() {
	    return fr.findAll().stream();
	  }
	  
	  
	  public void affectatFileToUser(int idfile, long id) {
          User user=ur.findById(id).get();
          File file=fr.findById(idfile).get();
          file.setUser(user);
          fr.save(file);
	 
	  }
	
	}