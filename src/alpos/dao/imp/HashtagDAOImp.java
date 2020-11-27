package alpos.dao.imp;

import alpos.dao.HashtagDAO;
import alpos.entity.Hashtag;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;


@Repository
public class HashtagDAOImp extends GenericDAOImp<Hashtag, Integer> implements HashtagDAO {
    public HashtagDAOImp() {
        super(Hashtag.class);
    }
    
    public Hashtag findByName (String hashtagName) {
    	try {
    		
    		return getHibernateTemplate().execute(new HibernateCallback<Hashtag>() {
	    		public Hashtag doInHibernate (Session session) throws HibernateException{
	    			@SuppressWarnings("unchecked")
					Query<Hashtag> query = session.createQuery("FROM Hashtag WHERE name = :name");
	    			query.setParameter("name", hashtagName);
	    			return query.uniqueResult();
	    		}
			});
	    }
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
}