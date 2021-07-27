package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface UserDao {
	//Remember that DAO: Data Access Object (Only CRUD Methods)
	
		//Create
		 public int registerEmployee(User employee);	
		 public int submitRequest(Reimbursement request);
		
		//Read
		 public List<User> getAllEmployees();
		 public User getEmployeeById(int id);
		 public User getUserByEmail(String email);
		 public String getUserPositionName(int userid);
		 public List<Reimbursement> getAllRequests();
		 public List<Reimbursement> getReimbursementsById(int id);
		 public List<Reimbursement> getReimbursementsByStatus(int status);
		 public User getUserByEmailAndPassword(String email, String password);
		 public List<Reimbursement> getReimbursementsBySubmissionIdAndStatus(int userId,int status);
		//Update
		 public int updateRequestStatus(int managerId,Reimbursement request,int status);
		 public int updateEmployeeName(User employee,String newFname,String newLname);
		 public int updateEmployeePassword(User employee, String newPassword);
		 
		//Delete- currently no method for delete functionality 
		 
		
}
// NOTE: This should really be in a doa interface class bc it is outlining all of the methods we will implement in another class 