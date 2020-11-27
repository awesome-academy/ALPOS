package alpos.dao;

import alpos.entity.Hashtag;

public interface HashtagDAO extends GenericDAO<Hashtag, Integer> {
    public Hashtag findByName (String hashtagName);
}
