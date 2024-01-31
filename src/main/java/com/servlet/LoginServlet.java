package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//show databases;
//use loginpage2;
//create table login_user(id varchar(20),password varchar(20));
//insert into login_user values("prince","12345");


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String  userid  = request.getParameter("userid");
		String password = request.getParameter("password");
		
		//JDBC CONNECTION
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginpage2","root","9860949212");
			Statement st=conn.createStatement();
			String query="select * from login_user where userid= '"+userid+"' and password='"+password+"'";
			ResultSet rs=st.executeQuery(query);
			
			if(rs.next()) {
				out.print("<br><br><h1  style=\"color: #7b26eb ;\">"+userid+": Welcome to Home page</h1><br>");
				out.print("<h1 style=\"color: green;\">Login Successfully!</h1><br>");
			}else {
				//the user id and password is not available in DB
				out.print("<br><br><h1 style=\"color: #d93d84\">"+userid+": Please enter correct userId and Password</h1><br>");
				out.print("<h1 style=\"color: red;\">Login Failed...!</h1><br>");
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.print("<br><br><h1 style=\"color: red;\">Login Failed...! because of server exception</h1><br>");
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}

}
