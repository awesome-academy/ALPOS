package alpos.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import alpos.entity.User;

public interface UserDAO extends GenericDAO<User, Integer> {
	
	public User findUserByEmail(String email) ;
	
	public User findUser(User user);

	public Page<User> paginate(Pageable pageable);
}
