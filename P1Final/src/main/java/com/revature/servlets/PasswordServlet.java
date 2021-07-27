package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserServiceMethods;

public class PasswordServlet extends HttpServlet {

UserServiceMethods service = new UserServiceMethods();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		BufferedReader bufferedReader = request.getReader();
		User employee = mapper.readValue(bufferedReader, User.class);
		
		service.updateEmployeePassword(employee, employee.getPassword());
	}

	
	
	
}
