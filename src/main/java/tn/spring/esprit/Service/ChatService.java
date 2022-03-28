package tn.spring.esprit.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.esprit.Repository.ChatRepository;
import tn.spring.esprit.entities.Chat;
import tn.spring.esprit.entities.Invitation;
import tn.spring.esprit.entities.User;

@Service
public class ChatService implements IChatService{
	@Autowired
	ChatRepository cr;
	@Override
	public void saveChat(Chat chat) {
		cr.save(chat);
		
	}
	@Override
	public void deleteChat(int Message_id ) {
		cr.deleteById(Message_id);
		
		
	}

		
		
	

	@Override
	public List<Chat> retrieveChats() {

		return cr.findAll();
	}
	

}
