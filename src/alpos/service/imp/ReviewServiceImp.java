package alpos.service.imp;

import alpos.dao.BlackListDAO;
import alpos.entity.BlackList;
import alpos.model.BlackListModel;
import alpos.model.UserModel;
import alpos.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import alpos.dao.HashtagDAO;
import alpos.dao.ReviewDAO;
import alpos.entity.Hashtag;
import alpos.entity.Review;
import alpos.model.ReviewModel;

import java.util.regex.*;


@Component
@Service
public class ReviewServiceImp implements ReviewService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private BlackListDAO blackListDAO;


	public void setBlackListDao(BlackListDAO blackListDAO) {
		this.blackListDAO = blackListDAO;
	}
	
	@Autowired
	private ReviewDAO reviewDao;
	
	@Autowired
	private HashtagDAO hashtagDao;
	
	public ReviewDAO getReviewDao() {
		return reviewDao;
	}

	public void setReviewDao(ReviewDAO reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	public HashtagDAO getHashtagDAO() {
		return hashtagDao;
	}


	public void setHashtagDao(HashtagDAO hashtagDAO) {
		this.hashtagDao = hashtagDAO;
	}


	@Transactional
	public void blackList(Integer reviewId, Integer userId) throws Exception {
		BlackList blackList = new BlackList();
		blackList.setReviewId(reviewId);
		blackList.setUserId(userId);
		blackListDAO.makePersistent(blackList);
	}

	public List<BlackListModel> findBlackListedReviewByUserId(Integer userId) {
		List<BlackListModel> blackListModelList = new ArrayList<BlackListModel>();
		try {
			List<BlackList> blackListList = blackListDAO.findBlackListedReviewByUserId(userId);
			for (BlackList blackList : blackListList) {
				BlackListModel blackListModel = new BlackListModel();
				BeanUtils.copyProperties(blackList, blackListModel);
				blackListModelList.add(blackListModel);
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching all users from the database", e);
		}
		return blackListModelList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<BlackListModel> paginate(BlackListModel blackListModel) {
		try {
			BlackList condition = new BlackList();
			condition.setUserId(blackListModel.getUser_id());
			Page<BlackList> blackLists = blackListDAO.paginate(condition, blackListModel.getPageable());
			return blackLists.map(blackList -> {
				BlackListModel model = new BlackListModel();
				BeanUtils.copyProperties(blackList, model);
				UserModel user = new UserModel();
				BeanUtils.copyProperties(blackList.getUser(), user);
				model.setUser(user);
				return model;

			});
			
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details by email from the database", e);
			return null;
		}
	}
	

	@Transactional
	public void addReview (ReviewModel reviewModel) {
		try {
			Review review = new Review();
			review.setUserId(reviewModel.getUserId());
			review.setBookId(reviewModel.getBookId());
			review.setContent(reviewModel.getContent());
			review.setHashtags(reviewModel.getHashtags());
			reviewDao.makePersistent(review);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	@Transactional
	public List<Hashtag> getHashtagFromReviewContent (String reviewContent){
		List<String> hashtags = new ArrayList<String>();
		List<Hashtag> hashtagObjects = new ArrayList<Hashtag>();
		
		//Extract hashtag from review content
		Pattern hashtagPattern = Pattern.compile("(#\\w+)\\b"); 
		Matcher matcher = hashtagPattern.matcher(reviewContent);
		while(matcher.find()) {
			hashtags.add(matcher.group().substring(1).toLowerCase());			
		}	
		
		//Convert hashtag string to hashtag Object
		
		try {
			while (hashtags != null) {
				for (String hashtag:hashtags) {
//					Hashtag hashtagObject = hashtagDAO.findByName(hashtag);
					if (hashtagDao.findByName(hashtag) != null) {
						hashtagObjects.add(hashtagDao.findByName(hashtag));
					}
					else {
						Hashtag hashtagObject = new Hashtag();
						hashtagObject.setName(hashtag);
						hashtagObject = hashtagDao.makePersistent(hashtagObject);
						hashtagObjects.add(hashtagObject);
					}
				}
				return hashtagObjects;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return hashtagObjects;
	}
}
