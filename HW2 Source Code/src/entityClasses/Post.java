package entityClasses;

import validation.ValidationException;
import java.time.LocalDateTime;


import java.util.ArrayList;

import java.util.HashSet;

public class Post {
	private String id;
	private String author;
	private String title;
	private String body;
		private String thread;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private ArrayList<String> replyIds;
	private HashSet<String> readByUsers;
	private boolean isDeleted;
	private HashSet<String> keywords;

	public Post() {
		this.replyIds = new ArrayList<String>();
		this.readByUsers = new HashSet<String>();
		this.keywords = new HashSet<String>();
		this.isDeleted = false;
		this.thread = "General";
	}
	
	public Post(String id, String author, String title, String body) {
		this();
		this.id = id;
		this.author = author;
this.title = title;
		this.body = body;
		this.createdAt = LocalDateTime.now();
		extractKeywords();
	}
	
	
	public Post(String id, String author, String title, String body, String thread) {
		this(id, author, title, body);
		if (thread != null && !thread.trim().isEmpty()) {
			this.thread = thread;
		} else {
this.thread = "General";
		}
	}

	public String getId() {
		return this.id;
		
		
	}

	public void setId(String id) {
		this.id = id;
		
		
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
		
	}
	

	public String getTitle() {
		return this.title;
		
	}

	public void setTitle(String title) {
		this.title = title;
		extractKeywords();
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
extractKeywords();
	}
	
	
	
	public String getThread() {
		return this.thread;
	}
	
	public void setThread(String thread) {
		if (thread != null && !thread.trim().isEmpty()) {
			this.thread = thread;
			
} else {
			this.thread = "General";
			
		}
	}
	
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
		
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	public ArrayList<String> getReplyIds() {
		return this.replyIds;
	}
	
	public void addReplyId(String replyId) {
		if (!this.replyIds.contains(replyId)) {
this.replyIds.add(replyId);
		}
	}
	
	public void removeReplyId(String replyId) {
		this.replyIds.remove(replyId);
	}
	
	
	public int getReplyCount() {
		return this.replyIds.size();
	}
	
	public HashSet<String> getReadByUsers() {
		return this.readByUsers;
	}
	
	
	public void markAsReadBy(String username) {
		this.readByUsers.add(username);
	}
	
	public boolean isReadBy(String username) {
		return this.readByUsers.contains(username);
	}
	
	public boolean isDeleted() {
		return this.isDeleted;
	}
	
	
	public void markAsDeleted() {
		this.isDeleted = true;
	}
	
	public HashSet<String> getKeywords() {
		return this.keywords;
	}
	
	public void addKeyword(String keyword) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			this.keywords.add(keyword.toLowerCase().trim());
		}
	}
	
	
	public boolean matchesKeyword(String searchTerm) {
		if (searchTerm == null || searchTerm.trim().isEmpty()) {
			return false;
		}
		
		String lowerSearch = searchTerm.toLowerCase().trim();
String[] keywordArray = this.keywords.toArray(new String[0]);
		
		for (int i = 0; i < keywordArray.length; i++) {
			if (keywordArray[i].contains(lowerSearch)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	private void extractKeywords() {
		this.keywords.clear();
		
		if (this.title != null) {
			String[] titleWords = this.title.toLowerCase().split(" ");
for (int i = 0; i < titleWords.length; i++) {
				String word = titleWords[i].replaceAll("[^a-z0-9]", "");
				if (word.length() >= 3) {
					this.keywords.add(word);
				}
			}
		}
		
		if (this.body != null) {
			String[] bodyWords = this.body.toLowerCase().split(" ");
			for (int i = 0; i < bodyWords.length; i++) {
String word = bodyWords[i].replaceAll("[^a-z0-9]", "");
				if (word.length() >= 3) {
					this.keywords.add(word);
				}
			}
		}
	}


	public void validate() {
		validateAuthor(author);
validateTitle(title);
		validateBody(body);
		validateThread(thread);
	}

	@Override
	public String toString() {
		return "Post: {id:" + id + ", author:" + author + ", title: " + title + 
		       ", body: " + body + ", thread: " + thread + ", replies: " + getReplyCount() + 
		       ", deleted: " + isDeleted + "}";
	}


	private void validateAuthor(String aut) {
		if (aut == null || aut.isBlank()) throw new ValidationException("Author is required");
		
if (aut.trim().length() < 4) throw new ValidationException("Invalid author username");
	}

	private void validateTitle(String tit) {
		if (tit == null || tit.isBlank()) throw new ValidationException("Title is requried");
		
		if (tit.trim().length() < 4) throw new ValidationException("Short Title");
		
		if (tit.trim().length() > 100) throw new ValidationException("Long Title");
	}

	private void validateBody(String bod) {
if (bod == null || bod.isBlank()) throw new ValidationException("Body is requried");

		if (bod.trim().length() < 10) throw new ValidationException("Short Body");
		
		if (bod.trim().length() > 10000) throw new ValidationException("Long Body");
	}
	
	
	private void validateThread(String thr) {
		if (thr == null || thr.isBlank()) throw new ValidationException("Thread is required");
	}
}