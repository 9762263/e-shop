package ru.unlimit;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IssueServlet
 */
@WebServlet("/IssueServlet")
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Cookie[] cookies = request.getCookies();
			
		try{
				
				for(Cookie cookie : cookies){	
					
				    if("email".equals(cookie.getName())){
						response.sendRedirect("/OrderServlet");	
				    	break;
				    }else{
				    	response.sendRedirect("Login.html");
				    }			

				}	

		
		}catch(NullPointerException e){
			System.out.println("ошибка");
		}
		
	}

}
