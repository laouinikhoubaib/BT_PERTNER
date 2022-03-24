package tn.spring.esprit.ServiceInterface;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


public interface PostAttachementInterface {

    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);
}
