package com.ty.passwordstore.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.passwordstore.dto.Details;
import com.ty.passwordstore.dto.User;
import com.ty.passwordstore.service.DetailsService;
import com.ty.passwordstore.service.UserService;

@WebServlet(value = "/details")
public class DetailsController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		String email = (String) httpSession.getAttribute("user");
		String password1 = (String) httpSession.getAttribute("password");

		String name = req.getParameter("userName");
		String password = req.getParameter("password");
		String website = req.getParameter("website");
		String description = req.getParameter("description");

		PrintWriter printWriter = resp.getWriter();

		Details details = new Details();
		details.setName(name);
		details.setPassword(password);
		details.setWebsite(website);
		details.setDescription(description);

		UserService userService = new UserService();
		User user = userService.getUser(email, password1);

		details.setUser(user);

		DetailsService detailsService = new DetailsService();
		Details details2 = detailsService.saveDetails(details);
		if (details2 != null) {
			printWriter.write("<html><body><h1>Your Details Saved Successfully</h1></body></html>");

			/*
			 * RequestDispatcher dispatcher = req.getRequestDispatcher("login.gtml");
			 * dispatcher.forward(req, resp);
			 */
		} else {
			printWriter.write("<html><body><h1>Please Register Your Valid Deatils </h1></body></html>");
			RequestDispatcher dispatcher = req.getRequestDispatcher("details.html");
			dispatcher.include(req, resp);
		}

	}

}
