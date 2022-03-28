package tn.spring.esprit.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.spring.esprit.Service.ChatService;
import tn.spring.esprit.entities.Chat;


@RestController
public class ChatController {
	ChatService cs;
	@DeleteMapping("/delete/{Message_id}")
	@ResponseBody
	public void deleteChat(@PathVariable("Message_id")int Message_id) {
		cs.deleteChat(Message_id);
	}
	@PostMapping("/saveChat")
	public void saveChat(Chat chat) {
		cs.saveChat(chat);
		}
	@GetMapping("/retrieve-allChat")

	public List<Chat> getallchats() {
		return cs.retrieveChats();
	}
		

}
