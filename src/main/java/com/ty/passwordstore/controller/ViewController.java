package com.ty.passwordstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.passwordstore.dto.Details;
import com.ty.passwordstore.dto.User;
import com.ty.passwordstore.service.UserService;

@WebServlet(value = "/view")
public class ViewController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		String email = (String) httpSession.getAttribute("user");
		String password = (String) httpSession.getAttribute("password");

		PrintWriter printWriter = resp.getWriter();

		UserService userService = new UserService();
		User user = userService.getUser(email, password);
		if (user != null) {
			List<Details> details = user.getDetails();
			if (details.size() > 0) {
				printWriter.write("<html><body>");
				printWriter.write("<table border=" + "solid" + ">");
				printWriter.write("<tr><th>Name</th><th>Password</th><th>Website</th><th>Description</th></tr>");

				for (Details details2 : details) {
					String name = details2.getName();
					String password1 = details2.getPassword();
					String website = details2.getWebsite();
					String description = details2.getDescription();

					printWriter.write("<tr><td>" + name + "</td><td>" + password1 + "</td><td>" + website + "</td><td>"
							+ description + "</td></tr>");

				}
				printWriter.write("</table");
				printWriter.write("</body></html>");
			} else {
				printWriter.write("<html><body><h1>No Details you have added</h1></body></html>");
			}
		} else {
			printWriter.write("<html><body><h1>Not a Authorised Person </h1></body></html>");

		}

	}
}
