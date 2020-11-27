package alpos.entity;

import java.io.Serializable;
import java.util.List;

public class Review implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private String  content;
    private List<Hashtag> hashtags;
    private User user;


	public Review() {
    }

    public Review(Integer id, Integer userId, Integer bookId, List<Hashtag> hashtags, String content) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.hashtags = hashtags;
        this.content = content;
    }

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
    
    public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
