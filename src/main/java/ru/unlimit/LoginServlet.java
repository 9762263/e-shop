package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Locale;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Radiator> list;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		Cookie cookie = new Cookie("email", email);
		cookie.setMaxAge(300);//время жизни куки в секундах!
		response.addCookie(cookie);
		
		
		PrintWriter out= response.getWriter();
		
		String id=session.getId();
		
		list = MyServlet.users.get(id); 
		
		JDBCExample db=new JDBCExample();
		User user = db.getDataUser(email);
		//List<Integer> orders=db.getOrdersUser(email);
		
		out.println("Гратуляцыя, "+user.name+" ! Вы успешно вошли в свой личный кабинет."
				+ "<br> Ваш номер телефона: "+user.phone);
		out.println("<br>");
		/*
		out.println("Ваши счёт-фактуры:");
		for(Integer i:orders){
			out.println("<br>");
			out.println(i);
			
		}
		*/
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

		int idSF = db.getLastidSF();
		
		++idSF;
		
		SzotFaktura.createPDF(idSF,user.name, "Sovetskaya 70","XXX",user.phone, list);
		for(Radiator rad1:list){
			db.insertRadiator(rad1.getType()+"-"+rad1.getSize(),rad1.getPrice(),rad1.getCount(),rad1.getCount()*rad1.getPrice(),idSF);	
		}
		
		db.insertBuyer(user.name, user.email, user.phone,"Sovetskaya 70" , "XXX",String.valueOf(idSF));
		
		out.println("<a href='Type.html'><p>В начало </p></a>");
		session.invalidate();
		out.close();
		
		System.out.println("м-д POST в LoginServlet"+id);
	}

}
