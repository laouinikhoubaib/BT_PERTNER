package tn.spring.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.spring.esprit.ServiceInterface.MailService;
import tn.spring.esprit.ServiceInterface.PostsInterface;
import tn.spring.esprit.entities.Mail;
import tn.spring.esprit.entities.Posts;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostsInterface postservice;
	@Autowired
	private MailService mailService;
	
	@PostMapping("/ajouter-post/{user_id}")
	public void addPost(@RequestBody Posts p,@PathVariable("user_id") int user_id) {
		 postservice.addPost(p,user_id);
		Mail mail = new Mail();
		mail.setMailFrom("lotfi.hammoudi@esprit.tn");
		mail.setMailTo("lotfihammoudi384@gmail.com");
		mail.setMailSubject("Nouvelle Post ajouter\"Dubai Connexion\" !");
		String mailContent = "Bonjour,\n"
				+ "\n"
				+ "Nous avons reçu une notification pour annoncer qui une nouvele post est ajouter"
				+ "\n"
				+ "Cordialement,\n";
		mail.setMailContent(mailContent);

		mailService.sendEmail(mail);
	}


	@GetMapping("/retrieveAllPosts")
	public List<Posts> getPosts() {
		return postservice.retrieveAllPosts();
	}

	@PutMapping("/update-post/{post-id}")
	public void updatePost(@RequestBody Posts newPost, @PathVariable("post-id") int id)
	{
		postservice.updatePost(newPost, id);
	}

	@DeleteMapping("/remove-post/{post-id}")
	public void removeClient(@PathVariable("post-id") int post_id) {
		postservice.deletePost(post_id);
}

    @GetMapping("/nombreReaction/{post-id}")
	public int nombreReaction (	 @PathVariable("post-id") int post_id){return postservice.nombreReaction(post_id); }

	@PostMapping("/SharePost/{post-id}/{user_id}")
	public String SharePost ( @PathVariable("post-id") int post_id,@PathVariable("user_id") int user_id){return postservice.SharePost(post_id,user_id);}

	@PutMapping("/affecterrecompense/{user_id}")
	public String affecterrecompense(@PathVariable("user_id")int user_id){return postservice.affecterrecompense(user_id);}
}