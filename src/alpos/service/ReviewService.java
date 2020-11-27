package alpos.service;

import java.util.List;
import alpos.entity.Hashtag;
import alpos.model.ReviewModel;
import org.springframework.data.domain.Page;

import alpos.model.BlackListModel;

public interface ReviewService {
	
	public void blackList(Integer reviewId, Integer userId) throws Exception;
	
	public List<BlackListModel> findBlackListedReviewByUserId(Integer userId);
	
	public Page<BlackListModel> paginate(BlackListModel blackListModel);
	
	public void addReview (ReviewModel reviewModel);
	
	public List<Hashtag> getHashtagFromReviewContent (String reviewContent);

}
