package springprojectfive.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import springprojectfive.model.User;


@Component
public class UserDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	// create
	@Transactional
	public void createUser(User user) {

		this.hibernateTemplate.saveOrUpdate(user);

	}

	// get all products
	public List<User> getUsers() {
		List<User> users = this.hibernateTemplate.loadAll(User.class);
		return users;
	}

	 // delete the single product
	@Transactional
	public void deleteUser(int uid) {
		User u = this.hibernateTemplate.load(User.class, uid);
		this.hibernateTemplate.delete(u);
	}

	// get the single product
		public User getUser(int uid) {
			return this.hibernateTemplate.get(User.class, uid);
		}
	
	

}
