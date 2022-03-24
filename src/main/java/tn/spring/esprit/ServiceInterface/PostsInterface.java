package tn.spring.esprit.ServiceInterface;



import java.util.List;

import tn.spring.esprit.entities.Posts;

public interface PostsInterface {
	
	public void addPost(Posts p ,int user_id);
	
	public void updatePost(Posts p, int id);
	
	public void deletePost(int id );
	
	public List <Posts> retrieveAllPosts();

	public int nombreReaction (int post_id);

	public String SharePost (int post_id , int user_id);

	public void affecterUser(int post_id,int user_id);

	public void affecterUserPosts(int post_id,int user_id);

	public String affecterrecompense(int user_id);
}
