package com.ty.passwordstore.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.passwordstore.dto.User;
import com.ty.passwordstore.service.UserService;

@WebServlet(value = "/user")
public class UserController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("userName");
		String email = req.getParameter("userEmail");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");

		PrintWriter printWriter = resp.getWriter();

		User user = new User();
		UserService userService = new UserService();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setGender(gender);
		User user2 = userService.saveUser(user);
		if (user2 != null) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.html");
			requestDispatcher.forward(req, resp);
		} else {
			String htmlCode = "<html><body><h1>Enter Your Valid Details</h1></body></html>";
			printWriter.write(htmlCode);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("user.html");
			requestDispatcher.include(req, resp);
		}

	}

}