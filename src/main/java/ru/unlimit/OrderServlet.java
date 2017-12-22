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

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Radiator> list;
		String email=null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		PrintWriter out= response.getWriter();
		
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
		out.println("<table width=500px ><tr><td width=100px></td>"
				+ "<td width=100px></td>"
				+ "<td width=200px align=right> <font size=5 color=green >Сумма покупок:</font> </td>"
				+ "<td width=100px align=center> <font size=5 color=red >"+String.format(Locale.CANADA,"%.2f",allSumm)+"</font></td></tr></table>");
		out.println("<hr>");
		
		Cookie[] cookies= request.getCookies();
			for(Cookie cookie : cookies){	
				
			    if("email".equals(cookie.getName())){
			    	email=cookie.getValue();
			    }			

			}	
	
		
		JDBCExample db=new JDBCExample();
		User user = db.getDataUser(email);
		
		out.println("<table cellspacing=10><tr><td align=right>ФИО</td>"
				+"<td align=left>"+user.name+"</td></tr>"+
				"<tr><td align=right>Номер телефона:</td>"
						+"<td align=left >"+user.phone+"</td></tr>"
						+ "</table>");
		
		int idSF = db.getLastidSF();
		
		++idSF;
		SzotFaktura.createPDF(idSF,"Ivan Ebanov", "Sovetskaya 70","XXX",user.phone, list);
		
		out.println("<a href='Type.html'><p>В начало </p></a>");
		session.invalidate();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
