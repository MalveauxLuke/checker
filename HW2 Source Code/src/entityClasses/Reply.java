package entityClasses;

import validation.ValidationException;

public class Reply {
	private String id;
	private String postId;
	private String author;
	private String title;
	private String body;
	
	// Creating Constructor methods
	public Reply() {}
	public Reply(String id, String postId, String author, String title, String body) {
		this.id = id;
		this.postId = postId;
		this.author = author;
		this.title = title;
		this.body = body;
	}
	
	// Creating getters and setters
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPostId() {
		return this.postId;
	}
	
	public void setPostId(String postId) {
		this.postId = postId;
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
	}
	
	public String getBody() {
		return this.body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public void validate() {
		validateAuthor(author);
		validateTitle(title);
		validateBody(body);
	}
	
	@Override
	public String toString() {
		return "Reply: {id:" + id +  ", postId: " + postId + ", author:" + author + ", title: " + title + ", body: " + body;
	}
	
//	private boolean validatePostId(String pID) {
//		
//		
//		return true;
//	}
	
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
}
