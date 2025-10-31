package storingPostsAndReplies;

import java.util.HashMap;
//import java.util.ArrayList;
import java.util.UUID;
import java.util.Optional;

import entityClasses.Reply;

public class ReplyStore {
	private HashMap<String, Reply> allReplies;

	public ReplyStore(){
		allReplies = new HashMap<>();
	}

	// Create

	public Reply create(String postId, String author, String title, String body) {
		String id = UUID.randomUUID().toString();
		Reply r = new Reply(id, postId, author.trim(), title.trim(), body.trim());
		r.validate();
		allReplies.put(id, r);
		return r;
	}

	// Read

	public Optional<Reply> read(String id) {
		return Optional.ofNullable(allReplies.get(id));
	}


	// Update

	public Reply update(Reply r) {
		if (r.getId() == null || !allReplies.containsKey(r.getId())) {
			System.out.println("Reply is not found");
			return r;
		}
		r.validate();
		allReplies.put(r.getId(), r);
		return r;
	}

	// Delete


	public boolean delete(String id) {
		return allReplies.remove(id) != null;
	}
}