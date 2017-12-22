package ru.unlimit;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	ArrayList<Radiator> list;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		JDBCExample db= new JDBCExample();
		
		List<Radiator> catalog=db.getCatalog();

		request.setAttribute("catalog", catalog);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin.jsp");
		rd.forward(request, response);
		
		/*
		PrintWriter out= response.getWriter();
		out.println("<p> ща тут засобачим таблицу прайса</p>");
		out.println("<table width=500px ><tr><th width=250px>Товар</th>"
				+ "<th width=250px>Цена</th></tr></table>");
		
		for(Radiator rad:catalog){
		out.println("<table width=750px><tr align=center><td width=250px >"+rad.type+"</td>"
		+"<td width=250px ><input type=text size=3 value="+rad.price+"></td>"
				+ "<td width=250px>the Beard</td></tr></table>");
		
		}
		out.close();
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
