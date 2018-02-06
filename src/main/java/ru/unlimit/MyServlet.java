package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
			
	public static ArrayList<Radiator> busketList=new ArrayList<Radiator>();
	
	protected static Map<String,ArrayList<Radiator>> users =new ConcurrentHashMap<>();
	
	/*
	 protected Map<String,Double> radiators =new ConcurrentHashMap<>();
	@Override
	public  void init() throws ServletException {
		super.init();
		//каталог
		radiators.put("ЛУ 10-304",43.79);
		radiators.put("ЛУ 11-304",46.74);
		radiators.put("ЛУ 10-305",52.78);
		radiators.put("ЛУ 11-305",56.88);
		radiators.put("ЛК 10-304",20.29);
		radiators.put("ЛК 11-304",22.73);
		radiators.put("ЛК 10-305",26.96);
		radiators.put("ЛК 11-305",30.95);
	}
	
	*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Radiator> busketList=new ArrayList<Radiator>();
		
		String type=request.getParameter("type");
		String size=request.getParameter("size");
		String count=request.getParameter("count");

		JDBCExample db= new JDBCExample();
		double price=db.getPrice(size);
			
		//Double price = radiators.get(type+"-"+size);
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		Radiator radiator=new Radiator(type,size,Integer.parseInt(count),price);
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*60*24*7);//время сессии в секундах
		
		session.setAttribute("korzina", busketList);
		String id=session.getId();

		busketList=users.get(id);
		if(busketList==null){
		ArrayList<Radiator> list3= new ArrayList<>();
		list3.add(radiator);
		busketList=list3;
		users.put(id, list3); 
		}else{
		busketList.add(radiator);	
		}

		PrintWriter out= response.getWriter();
		out.println("<p>Ваша корзина :</p>"
				+ "<hr>");
		out.println("<table width=500px ><tr><th width=200px>Товар</th>"
				+ "<th width=100px>Цена</th>"
				+ "<th width=100px>Кол-во</th>"
				+ "<th width=100px>Стоимость</th></tr></table>");
		double allSumm=0;
		for(Radiator rad1:busketList){
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
		out.println("<table width=1000px><tr>"
				+"<td width=150px align=left><a href='Type.html'>Купить ещё</a></td>"+
				"<td width=350px align=right><form action='IssueServlet' method='POST'>"
				+ "<input type='submit' value='Оформить'>"
				+ "</form>  </td></tr></table>");
		

		out.close();
		System.out.println("м-д GET в MyServlet"+id);		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String id=session.getId();
		busketList=users.get(id);
		
		Buyer b1=new Buyer(request.getParameter("name"),request.getParameter("email"),request.getParameter("phone"),
				request.getParameter("adres"),request.getParameter("passport"));
		
		
		JDBCExample db= new JDBCExample();

		int idSF = db.getLastidSF();
		
		++idSF;
		
		for(Radiator rad1:busketList){
			db.insertRadiator(rad1.getType()+"-"+rad1.getSize(),rad1.getPrice(),rad1.getCount(),rad1.getCount()*rad1.getPrice(),idSF);	
		}
		
		db.insertBuyer(b1.name, b1.email, b1.phone, b1.adres, b1.passport,String.valueOf(idSF));
		
		SzotFaktura.createPDF(idSF,b1.name, b1.adres,b1.passport,b1.phone, busketList);
		/*
		try {
			SenderEMail.sendEMail(idSF,b1.email);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		*/
		
		PrintWriter out= response.getWriter();
		out.println("на указанный e-mail отправлена счёт-факутра");
		out.println("<br><a href='Type.html'>В начало</a>");
		session.invalidate();
		out.close();
	//	System.out.println(users);
		System.out.println("м-д POST в MyServlet"+id);
	//	users.remove(id);
		
	}
}
