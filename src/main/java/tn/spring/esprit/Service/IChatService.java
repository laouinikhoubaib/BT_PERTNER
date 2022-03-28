package tn.spring.esprit.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.spring.esprit.entities.Chat;
import tn.spring.esprit.entities.User;
@Service
public interface IChatService {
	public void saveChat(Chat chat);
	public void deleteChat(int Message_id);
	
	public List<Chat> retrieveChats()
;
}
