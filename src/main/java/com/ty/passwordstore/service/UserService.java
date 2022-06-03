package com.ty.passwordstore.service;

import com.ty.passwordstore.dao.UserDao;
import com.ty.passwordstore.dto.User;

public class UserService {
	UserDao userDao = new UserDao();

	public User saveUser(User user) {
		return userDao.saveUser(user);

	}

	public User validateUser(String email, String password) {
		return userDao.validateUser(email, password);

	}

	public User getUser(String email, String password) {
		return userDao.getUser(email, password);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);

	}

	public User updateUser(User user, int id) {
		return userDao.updateUser(user, id);

	}

	public boolean deleteUser(int id) {
		return userDao.deleteUser(id);

	}

}
