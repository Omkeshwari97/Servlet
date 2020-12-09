package com.capgemini.firstservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	description = "Login Servlet Testing",
	urlPatterns = {"/LoginServlet"},
	initParams = {
		@WebInitParam(name = "user", value = "Omkeshwari"),
		@WebInitParam(name = "password", value = "abcd")
	}
)
public class LoginServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//request parameters for userId and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		//servlet config init params
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		
		if(user.matches("^[A-Z]{1}[A-Za-z]{2,}$") && pwd.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$"))
		{
			request.setAttribute("user", user);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			
			if(!user.matches("^[A-Z]{1}[A-Za-z]{2,}$"))
			{
				out.println("<font color=red>User name is incorrect.</font>");
			}
			else if(!pwd.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$"))
			{
				out.println("<font color=red>Password is incorrect.</font>");
			}

			rd.include(request, response);
		}
	}

}
