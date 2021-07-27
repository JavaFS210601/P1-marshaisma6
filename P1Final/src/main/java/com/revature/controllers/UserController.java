package com.revature.controllers;
import java.io.BufferedReader;
//
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.LoginService;
//
public class UserController {

	private LoginService ls = new LoginService();
	private ObjectMapper om = new ObjectMapper(); //ObjectMapper is part of Jackson api, used to 
												  //transform data from JSON -> Java or Java -> JSON
	public void submitRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equals("POST")) {
			//reads the request JSON string into a buffered reader
			//in order to build a JAVA string out of JSON 
			BufferedReader buffReader = req.getReader();
			StringBuilder sBuilder = new StringBuilder();
			String sLine = buffReader.readLine();
			
			//building the JAVA string line by line until null
			while(sLine != null) {
				sBuilder.append(sLine);
				sLine = buffReader.readLine();
			}
			
			//passing the built JAVA string into a new string
			String sBody = new String(sBuilder);
			
			//mapping the JAVA string to the loginDTO object
			Reimbursement reimburse = om.readValue(sBody, Reimbursement.class);
			
			//if(ls.submitRequest(reimburse)){
				res.setStatus(200);
			}
			else {
				res.setStatus(500);
			}
		}
	}
	

	
	
	
	
	
	
	
	
//	//whenever you're working with HttpServletRequests/Responses, your method will need to throw an IOException
//	public void getAllAvengers(HttpServletResponse res) throws IOException {
//		
//		//get the List of employees from the service layer (which got it from the dao layer)
//		List<User> userList = as.getAllUsers();
//		
//		//turn that List in a JSON String
//		String json = om.writeValueAsString(userList);
//
//		//put the JSON into the response object (res)
//		res.getWriter().print(json);
//		
//		
//		
//		//override the default 404 status code that we set in the MasterServlet
//		res.setStatus(200);
//		
//	}
//	
//}
