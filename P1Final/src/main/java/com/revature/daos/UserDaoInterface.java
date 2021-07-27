package com.revature.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.UserRole;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDaoInterface implements UserDao {
	@Override
	public User getUserByEmail(String email) {
		User user=null; /*is not creating any object, just creating a reference to nothing.
						 because there is no reason to create an empty object since our SQL statement  
						will already create and return an object.
						NOTE: If I did User user = new user, this would be creating an object
						*/
		
		//What is the method code below going to contain? JDBC Code!
		//The query occurs in the try block. The catch block catches any 
		//SQLExceptions that occur, print an error message, then throw the exception. 
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users WHERE user_email= ?";
			PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement that will be sent to the database
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(5),resultSet.getInt(6));
			}
			
		} catch (Exception e) {
			System.out.println("Attempting to retrieve user by email failed");
			e.printStackTrace();
			/* REMEMBER: 'e' is just a variable. ('e' stands for exception, but I can rename it anything I want, 
			 * however, the data type has to remain 'Exception') The 'e' variable stores an exception-type object in this case.

			The 'catch' block will literally 'catch'' an exception object that was 'thrown' at some point during a 'try'
			 block and store it in the 'e' variable (a.k.a parameter)*/
		}
		return user; //in this case, if an exception occurs, nothing will be returned 
					//If i used User user = new user(), it would return a default instance of an User object instead of null which is unnecessary 
	}
	
	
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		User user=null;
		//What is the method code below going to need to contain? JDBC Code!
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users WHERE user_email= ? AND ers_password=?";
			PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement without the parameter values
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Attempting to retrieve user by email and password failed :(");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public String getUserPositionName(int positionId) {
		UserRole position = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
		String sql = "SELECT * FROM ers_user_roles WHERE ers_user_role_id= ?";
		PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement without the parameter values
		ps.setInt(1, positionId);
		ResultSet resultSet = ps.executeQuery();
		if(resultSet.next()) {
			position = new UserRole (resultSet.getInt(1),resultSet.getString(2));
		}

		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return position.getuser_role();
}

	@Override
	public int registerEmployee(User employee) {
		int rowsAffected = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO ers_users(user_email,ers_password,user_first_name,user_last_name,user_role_id,ers_username) VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement without the parameter values
			ps.setString(1, employee.getEmail()); 
			ps.setString(2, employee.getPassword());
			ps.setString(3, employee.getFname());
			ps.setString(4, employee.getLname());
			ps.setInt(5, employee.getPositionId());
			ps.setString(6, employee.getEmail());
			rowsAffected = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public List<Reimbursement> getAllRequests() {
		List<Reimbursement> requests = new ArrayList(); //is creating an object, an empty list in this case.
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM P1.ers_reimbursement";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				requests.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBlob(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public int submitRequest(Reimbursement request) {
		int rowsAffected = 0;
		try(Connection conn = ConnectionUtil.getConnection();) {
		int type = request.getRequestTypeId();
		int amount = request.getAmount();
		int statusId = 2;
		int author = request.getSubmissionUId();
		String description = request.getDescription();
		String submittedTime = (Timestamp.from(Instant.now())).toString();
		String sql = "Insert into ers_reimbursement(reimb_type_id,reimb_amount,reimb_description,"
				+ "reimb_submitted,reimb_status_id,reimb_author) "
				+ "VALUES("+type+","+amount+",'"+description+"','"+submittedTime+"',"+statusId+",'"+author+"')";//might have to alter later 
			
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setInt(1,request.getRequestTypeId());
//		ps.setInt(2, request.getSubmissionUId());
//		ps.setInt(3, request.getAmount());
//		ps.setString(4, request.getDescription());
		
		rowsAffected = conn.createStatement().executeUpdate(sql);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public List<Reimbursement> getReimbursementsById(int id) {   // Get all the request for a specific employee
		List<Reimbursement> requests = new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				requests.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBlob(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatus(int status) {
		List<Reimbursement> requests = new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				requests.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBlob(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
	}
	@Override
	public List<Reimbursement> getReimbursementsBySubmissionIdAndStatus(int userId, int statusId) {
		List<Reimbursement> requests = new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id="+statusId+" AND reimb_author="+userId;
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, statusId);
//			ps.setInt(2, userId);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				requests.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBlob(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
	}
	@Override
	public int updateEmployeeName(User employee,String newFname,String newLname) {
		int rowsAffected = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE ers_users SET user_first_name = ?, user_last_name = ? where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement without the parameter values
			ps.setString(1, newFname);
			ps.setString(2, newLname);
			ps.setInt(3, employee.getuId());
			rowsAffected = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	@Override
	public int updateEmployeePassword(User employee,String newPassword) {
		int rowsAffected = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE ers_users SET ers_password = ? where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement without the parameter values
			ps.setString(1, newPassword);
			ps.setInt(2, employee.getuId());
			rowsAffected = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	@Override
	public int updateRequestStatus(int managerId,Reimbursement request,int status) {
		System.out.println("Hits Dao");
		int rowsAffected = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE ers_reimbursement SET reimb_status_id = ?,reimb_resolver= ? WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement without the parameter values
			ps.setInt(1, status);
			ps.setInt(2, managerId);
			ps.setInt(3, request.getrId());
			rowsAffected = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}
	@Override
	public List<User> getAllEmployees() {
		List<User> users = new ArrayList<User>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users";
			PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement without the parameter values
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				users.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7)));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	@Override
	public User getEmployeeById(int id) {
		User user = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users WHERE ers_users_id="+id;
//			PreparedStatement ps = conn.prepareStatement(sql); //pre-compiling the sql statement without the parameter values
//			ps.setInt(1,id);
//			ResultSet resultSet = ps.executeQuery();
			ResultSet resultSet = conn.createStatement().executeQuery(sql);
			while(resultSet.next()) {
			 user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
