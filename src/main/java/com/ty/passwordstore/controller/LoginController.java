package com.ty.passwordstore.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.passwordstore.dto.User;
import com.ty.passwordstore.service.UserService;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		PrintWriter printWriter = resp.getWriter();

		UserService userService = new UserService();
		User user = userService.validateUser(email, password);

		if (user != null) {
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("user", email);
			httpSession.setAttribute("password", password);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.html");
			requestDispatcher.forward(req, resp);
		} else {
			printWriter.write("<html><body><h1>Entered The Wrong Credentials Please Try Again</h1></body></html>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.html");
			requestDispatcher.include(req, resp);
		}

	}
}
