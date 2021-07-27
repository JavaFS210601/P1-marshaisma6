package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.UserServiceMethods;

public class AllReimbursement extends HttpServlet {
	
	UserServiceMethods service = new UserServiceMethods();
	
	
	//url -/reimbursement (this endpoint will get all emp requests) if... want a single transaction create an endpoint for - /request/{id}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
			System.out.println("doGet---");
			List<Reimbursement> reimbursements = service.getAllRequest();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(reimbursements);
			resp.setContentType("application/json");
			PrintWriter pw = resp.getWriter();
			pw.print(json);
			
		}
		
		@Override
		protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException {
			System.out.println("Post");
			ObjectMapper mapper = new ObjectMapper();
			BufferedReader bufferedReader = req.getReader();
			Reimbursement request = mapper.readValue(bufferedReader,Reimbursement.class);
			System.out.println(request);
			System.out.println(request);
			service.submitRequest(request);
			
			
			
		}
		

}
