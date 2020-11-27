package alpos.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;


import alpos.entity.Hashtag;

public class ReviewModel extends BaseModel {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    @NotEmpty (message="{review.validation.no.content}")
    private String  content;
    private List<Hashtag> hashtags;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Hashtag> getHashtags() {
		return hashtags;
	}
	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
}
