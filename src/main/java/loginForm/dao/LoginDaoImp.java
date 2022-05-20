package loginForm.dao;


import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import loginForm.model.User;
import loginForm.security.Md5Hash;

@Repository
@Transactional
public class LoginDaoImp implements LoginDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public User checkLogin(String username, String password) {
		String hashPassword = null;
		try {
			hashPassword = Md5Hash.passwordHash(password);
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User u where u.username =: username and u.password =: password";
		System.out.println(hql);
		Query<User> query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", hashPassword);
		User user = query.uniqueResult();
		return user;
	}
}
