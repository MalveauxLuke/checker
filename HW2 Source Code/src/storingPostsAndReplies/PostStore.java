package storingPostsAndReplies;

import java.util.HashMap;
// import java.util.ArrayList;
import java.util.UUID;
import java.util.Optional;

import entityClasses.Post;

public class PostStore {
	private HashMap<String, Post> allPosts;

	public PostStore(){
		allPosts = new HashMap<>();
	}

	// Create

	public Post create(String author, String title, String body) {
		String id = UUID.randomUUID().toString();
		Post p = new Post(id, author, title, body);
		p.validate();
		allPosts.put(id, p);
		return p;
	}

	// Read

	public Optional<Post> read(String id) {
		return Optional.ofNullable(allPosts.get(id));
	}

	// Update

	public Post update(Post p) {
		if (p.getId() == null || !allPosts.containsKey(p.getId())) {
			System.out.println("Page is not found");
			return p;
		}
		p.validate();
		allPosts.put(p.getId(), p);
		return p;
	}

	// Delete

	public boolean delete(String id) {
		return allPosts.remove(id) != null;
	}

}