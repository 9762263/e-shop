package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String adres = request.getParameter("adres");
		String passport = request.getParameter("passport");
		String parol = request.getParameter("parol");
		
		PrintWriter out= response.getWriter();
		
		JDBCExample db=new JDBCExample();
		db.insertUser(name,email,phone,adres,passport,parol);

		out.println("Гратуляцыя, "+name+" ! Вам больше не нужно постоянно вводить данные");
		//response.sendRedirect("Issue.html");
		
		ArrayList<Radiator> list;
		String id=session.getId();
		
		list = MyServlet.users.get(id); 
		
		out.println("<p>Ваша текущая покупка</p>");
		out.println("<table width=500px ><tr><th width=200px>Товар</th>"
				+ "<th width=100px>Цена</th>"
				+ "<th width=100px>Кол-во</th>"
				+ "<th width=100px>Стоимость</th></tr></table>");
		double allSumm=0;
		for(Radiator rad1:list){
			out.println("<table width=700px><tr align=center><td width=200px >"+rad1.getType()+"-"+rad1.getSize()+"</td>"
					+"<td width=100px >"+rad1.getPrice()+"</td>"		
					+"<td width=100px >"+rad1.getCount()+"</td>"+
					"<td width=100px >"+String.format(Locale.CANADA,"%.2f",rad1.getPrice()*rad1.getCount())
								+ "</td>"
								+ "<td width=200px>Удалить</td></tr></table>");
					allSumm=allSumm+rad1.getPrice()*rad1.getCount();
			
		}
		
		//User user=new User(name,email,phone,adres,passport,parol);
		//out.println("<form action='RegistrationServlet' method='POST'");
		out.println("<p><input type='submit' value='Оформить заказ'></p>");
		//out.println("</form>");
		out.close();
		
	}

}
