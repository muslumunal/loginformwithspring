package loginForm.dao;

import loginForm.model.User;

public interface LoginDao {

	public User checkLogin(String username, String password);

}
