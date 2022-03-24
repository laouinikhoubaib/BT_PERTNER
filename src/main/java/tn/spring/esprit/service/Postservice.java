package tn.spring.esprit.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.esprit.ServiceInterface.PostsInterface;
import tn.spring.esprit.entities.*;
import tn.spring.esprit.repository.PostsRepository;
import tn.spring.esprit.repository.UserRepository;


@Service
public class Postservice implements PostsInterface {
	
	@Autowired
	PostsRepository postrepository;
	@Autowired
	UserRepository userRepository;


	@Override
	public void addPost(Posts p,int user_id) {


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
		//mailService.sendEmail(mail);

		postrepository.save(p);
		affecterUser(p.getPostId(),user_id);
		affecterUserPosts(p.getPostId(),user_id);
	}


	@Override
	public void updatePost(Posts newPost, int id)
	{
		Posts post = postrepository.findById(id).orElse(null);
		post.setPostDate(newPost.getPostDate());
		post.setPostContent(newPost.getPostContent());
		post.setDictionary(newPost.getDictionary());
		post.setUser(newPost.getUser());
		postrepository.save(post);
	}

	@Override
	public void deletePost(int id) {
		postrepository.deleteById(id);
		
	}

	@Override
	public List<Posts> retrieveAllPosts() {
		return (List<Posts>) postrepository.findAll();
		
	}
	@Override
	public int nombreReaction (int post_id){
		Posts poster = postrepository.findById(post_id).orElse(null);
        //int size=poster.getListPostsReactions().size();
		return poster.getListPostsReactions().size();

	}

@Override
	public String SharePost (int post_id , int user_id){
	User user = userRepository.findById(user_id).orElse(null);
	Posts poster = postrepository.findById(post_id).orElse(null);
	if (user.getPosts() == null) {
		Set <Posts> ListPosts = new HashSet<>();
		ListPosts.add(poster);
		user.setPosts(ListPosts);
		userRepository.save(user);
	}else {
		user.getPosts().add(poster);
		userRepository.save(user);
	}
	return "post partager avec succee";


}
// affecter posts lil user
	@Override
	public void affecterUser(int post_id,int user_id) {
		User user = userRepository.findById(user_id).orElse(null);
		Posts post = postrepository.findById(post_id).orElse(null);

		if (user.getPosts() == null) {
			Set<Posts> posts = new HashSet<>();
			posts.add(post);
			user.setPosts(posts);
			userRepository.save(user);
		} else
			user.getPosts().add(post);
	}
	

	@Override
	public void affecterUserPosts(int post_id,int user_id) {
		User user = userRepository.findById(user_id).orElse(null);
		Posts post = postrepository.findById(post_id).orElse(null);
       if (post != null) {
		   post.setUser(user);
		   postrepository.save(post);
	   }else System.out.println("post n'existe pas");
	}
	@Override
	public String affecterrecompense(int user_id){
		User user = userRepository.findById(user_id).orElse(null);
		if (user.getPosts().size()>1 && user.getPosts().size()<4 ){
			user.setRecompense(Recompense.medaille_Bronze);
		}else if (user.getPosts().size()>4 && user.getPosts().size()<6){
			user.setRecompense(Recompense.medaille_argent);
		}else  if (user.getPosts().size()>6){

			user.setRecompense(Recompense.medaille_or);
		}else {user.setRecompense(null);}
return ("la recompense "+ user.getRecompense()+" a eté affecté a utlisateur " + user_id +" avec succes ");
	}
}
