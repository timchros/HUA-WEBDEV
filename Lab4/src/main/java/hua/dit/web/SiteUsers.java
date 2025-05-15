package hua.dit.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hua.dit.web.data.CreateUserInputData;
import hua.dit.web.data.CreateUserOutputData;
import hua.dit.web.data.DeteteUserOutputData;
import hua.dit.web.data.GetUsersOutput;
import hua.dit.web.db.LabDb;
import hua.dit.web.db.User;

/**
 * REST-style Web Service
 */
@WebServlet("/SiteUsers/*")
public class SiteUsers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public SiteUsers() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	System.out.println("Service Initialization (if needed)...");
    	super.init();
    }

    /** Update User Data - not implemented */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	throw new UnsupportedOperationException("doPost() not implemented !");
	}
	
    /** Get User (row) or Users (rows) */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Check if we have requested for one specific User or ALL of them 
			// Try getting User ID from URL
			String uid_str = ServletUtil.getPathFirstParam(request);
			
			if (uid_str == null) {
			
				// Get ALL Users from DB
				final List<User> userList = LabDb.getUsers();
				
				// Send HTTP response with JSON data
				GetUsersOutput outputData = new GetUsersOutput(userList.size(), userList);
				ServletUtil.sendResponseData(outputData, response);
			
			} else {
				
				// Process Data - Get Specific User from DB
				int uid = Integer.parseInt(uid_str);
				final List<User> userList = new ArrayList<User>();
				userList.add(LabDb.getUser(uid));
				
				// Send HTTP response with JSON data
				GetUsersOutput outputData = new GetUsersOutput(userList.size(), userList);
				ServletUtil.sendResponseData(outputData, response);
			
			}
		} catch (Throwable t) {
			t.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, t.getMessage());
		}
	} // END OF doGet(..)
    
	/** Insert User (row) to DB */
    @Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get User Data from the JSON existing in the HTTP Request Body
			CreateUserInputData inputData = ServletUtil.getRequestData(CreateUserInputData.class, request);
			
			// Process User Data and Store them to the DB
			final String username = inputData.getUsername();
			final String password_hash = Util.getHash256(inputData.getPassword());
			boolean insert_flag = LabDb.insertUserData(username, password_hash);
			
			// Send HTTP response with JSON data
			CreateUserOutputData outputData = new CreateUserOutputData(insert_flag);
			ServletUtil.sendResponseData(outputData, response);
			
		} catch (Throwable t) {
			// t.printStackTrace();
			// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, t.getMessage());
			
			// Send HTTP response with JSON data
			CreateUserOutputData outputData = new CreateUserOutputData(false);
			ServletUtil.sendResponseData(outputData, response);
		}
	} // END OF doPut(..)
	
    /** Remove User (row) from DB */
    @Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get User ID from URL
			String uid_str = ServletUtil.getPathFirstParam(request);
			
			// Process Data - Remove User from DB
			int uid = Integer.parseInt(uid_str);
			boolean delete_flag = LabDb.deleteUser(uid);
			
			// Send HTTP response with JSON data
			DeteteUserOutputData outputData = new DeteteUserOutputData(delete_flag, 1);
			ServletUtil.sendResponseData(outputData, response);
			
		} catch (Throwable t) {
			t.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, t.getMessage());
		}
	} // END OF doDelete(..)

}
