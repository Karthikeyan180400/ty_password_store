package com.ty.passwordstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.passwordstore.dto.User;

public class UserDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public User saveUser(User user) {

		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		return user;

	}

	public User validateUser(String email, String password) {
		entityManager = entityManagerFactory.createEntityManager();
		String sql = "select u from User u where u.email=?1 and u.password=?2";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<User> users = query.getResultList();
		for (User user : users) {
			return user;
		}
		return null;

	}

	public User getUser(String email, String password) {
		entityManager = entityManagerFactory.createEntityManager();
		String sql = "select u from User u where u.email=?1 and u.password=?2";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<User> users = query.getResultList();
		for (User user : users) {
			return user;
		}
		return null;
	}

	public User getUserById(int id) {
		return null;

	}

	public User updateUser(User user, int id) {
		return user;

	}

	public boolean deleteUser(int id) {
		return false;

	}

}
