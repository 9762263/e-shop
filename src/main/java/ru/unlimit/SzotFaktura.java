package ru.unlimit;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SzotFaktura {
	
	
	public static void createPDF(int idSF,String buyer,String adres,String passport,String tel,ArrayList<Radiator> basketList) throws 
	MalformedURLException, IOException{
		// Listing 1. Instantiation of document object
		Document document = new Document(PageSize.A4, 30, 30, 30, 30);//размер и поля
		try{
			
		BaseFont baseFont = BaseFont.createFont(System.getProperty("user.home")+"/workspace/WebApp/10535.ttf", "cp1251", true);
		Font font1 = new Font(baseFont,10);
		Font font2 = new Font(baseFont,9);
		Font font3 = new Font(baseFont,11,Font.BOLD);
		// Listing 2. Creation of PdfWriter object
		PdfWriter.getInstance(document,new FileOutputStream("E:\\Счёт-фактура№"+idSF+".pdf"));
		
		document.open();
		Paragraph p1=new Paragraph("Отгрузка товара производится при наличии оригинальной доверенности, паспорта, подписанной счёт-фактуры с оригинальной печатью по адресу: г.Лида, ул.Советская 70",font3);
		p1.setAlignment(1);
		p1.setSpacingAfter(10);
		document.add(p1);
		
		
		PdfPTable t = new PdfPTable(2);
		t.setHorizontalAlignment(0);
		
		t.setLockedWidth(true);
		t.setTotalWidth(550f);
		float[] widthsTable1=new float[]{150f,400f};
		t.setWidths(widthsTable1);
		
		PdfPCell c1 = new PdfPCell();
		c1.setBorder(0);
		Image logo = Image.getInstance("logo.jpg");
		
		logo.scaleAbsolute(110f, 65f);
		logo.setAlignment(1);
		c1.addElement(logo);
		Paragraph webSite=new Paragraph("www.shop.lidea.by",font1);
		webSite.setAlignment(1);
		c1.addElement(webSite);
		t.addCell(c1);
		
		PdfPCell c2 = new PdfPCell();
		c2.setBorder(0);
		
		Paragraph tp1=new Paragraph("Продавец:",font1);
		tp1.setAlignment(0);
		c2.addElement(tp1);
		Paragraph tp2=new Paragraph("Открытое акционерное общество Упраляющая комапания холдинга Лидсельмаш ",font1);
		tp2.setAlignment(0);
		c2.addElement(tp2);
		Paragraph tp3=new Paragraph("231300,РБ,Гродненская обл,г.Лида,ул.Советская,70",font1);
		tp3.setAlignment(0);
		c2.addElement(tp3);
		Paragraph tp4=new Paragraph("тел./факс +375 154 52-16-28",font1);
		tp4.setAlignment(0);
		c2.addElement(tp4);
		Paragraph tp5=new Paragraph("р/с 3012266563878,в ЦБУ 228 ОАО Белинвестбанк",font1);
		tp5.setAlignment(0);
		c2.addElement(tp5);
		Paragraph tp6=new Paragraph("г.Лида ,ул.Мицкевича,39",font1);
		tp6.setAlignment(0);
		c2.addElement(tp6);
		Paragraph tp7=new Paragraph("УНП 500021638    МФО 153001739   ОКПО 00236085",font1);
		tp7.setAlignment(0);
		c2.addElement(tp7);
		t.addCell(c2);
		t.setSpacingAfter(15);
		
		document.add(t);
		
		GregorianCalendar rightNow = new GregorianCalendar();
		Paragraph p2=new Paragraph("Счёт-фактура номер "+idSF+" от "+String.format("%1$te %1$tB %1$tY г", rightNow),font3);
		p2.setAlignment(1);
		p2.setSpacingAfter(10);
		document.add(p2);
		
		GregorianCalendar deadLine=new GregorianCalendar();
		deadLine.setTimeInMillis(rightNow.getTimeInMillis()+12*24*60*60*1000);
		Paragraph p3=new Paragraph("Счёт-фактура действительна до "+String.format("%1$te %1$tB %1$tY г", deadLine),font1);
		p3.setAlignment(1);
		p3.setSpacingAfter(10);
		document.add(p3);
		
		Paragraph p4=new Paragraph("Покупатель: "+ buyer,font1);
		p4.setAlignment(0);
		p4.setSpacingAfter(1);
		document.add(p4);
		
		Paragraph p5=new Paragraph("Адрес: "+ adres,font1);
		p5.setAlignment(0);
		p5.setSpacingAfter(1);
		document.add(p5);
		
		Paragraph p6=new Paragraph("Паспорт: "+ passport,font1);
		p6.setAlignment(0);
		p6.setSpacingAfter(1);
		document.add(p6);
		
		Paragraph p7=new Paragraph("тел: "+ tel,font1);
		p7.setAlignment(0);
		p7.setSpacingAfter(10);
		document.add(p7);
		
		PdfPTable basket = new PdfPTable(11);
		basket.setHorizontalAlignment(0);
		basket.setLockedWidth(true);
		basket.setTotalWidth(550f);
		float[] widthsTableBasket=new float[]{25f,75f,60f,30f,50f,55f,40f,70f,70f,35f,40f};
		basket.setWidths(widthsTableBasket);
		
		PdfPCell cell1 = new PdfPCell();
		Paragraph nn=new Paragraph("п/п:",font1);
		nn.setAlignment(1);
		cell1.addElement(nn);
		basket.addCell(cell1);
		
		PdfPCell cell2 = new PdfPCell();
		Paragraph name=new Paragraph("Наименование",font1);
		name.setAlignment(1);
		cell2.addElement(name);
		basket.addCell(cell2);
		
		PdfPCell cell3 = new PdfPCell();
		Paragraph type=new Paragraph("Тип/Размер",font1);
		type.setAlignment(1);
		cell3.addElement(type);
		basket.addCell(cell3);
		
		PdfPCell cell4 = new PdfPCell();
		Paragraph count=new Paragraph("кол-во,шт",font1);
		count.setAlignment(1);
		cell4.addElement(count);
		basket.addCell(cell4);
		
		PdfPCell cell5 = new PdfPCell();
		Paragraph price=new Paragraph("Цена,руб.коп.",font1);
		price.setAlignment(1);
		cell5.addElement(price);
		basket.addCell(cell5);
		
		PdfPCell cell6 = new PdfPCell();
		Paragraph value=new Paragraph("Стоимость,руб.коп.",font1);
		value.setAlignment(1);
		cell6.addElement(value);
		basket.addCell(cell6);
		
		PdfPCell cell7 = new PdfPCell();
		Paragraph NDS=new Paragraph("Ставка НДС,%",font1);
		NDS.setAlignment(1);
		cell7.addElement(NDS);
		basket.addCell(cell7);

		PdfPCell cell8 = new PdfPCell();
		Paragraph valueNDS=new Paragraph("Сумма НДС,руб.коп.",font1);
		valueNDS.setAlignment(1);
		cell8.addElement(valueNDS);
		basket.addCell(cell8);
		
		PdfPCell cell9 = new PdfPCell();
		Paragraph totalPrice=new Paragraph("Стоимость с НДС,руб.коп.",font1);
		totalPrice.setAlignment(1);
		cell9.addElement(totalPrice);
		basket.addCell(cell9);
		
		PdfPCell cell10 = new PdfPCell();
		Paragraph weight=new Paragraph("Вес,кг/шт",font1);
		weight.setAlignment(1);
		cell10.addElement(weight);
		basket.addCell(cell10);
		
		PdfPCell cell11 = new PdfPCell();
		Paragraph mass=new Paragraph("Масса груза,кг",font1);
		mass.setAlignment(1);
		cell11.addElement(mass);
		basket.addCell(cell11);
		
		int numberPosition=1;
		double allSumm=0;
		int allCount=0;
		for(Radiator rad:basketList){
			
			PdfPCell cellNumberOfPosition = new PdfPCell();
			Paragraph numberOfPosition=new Paragraph(String.valueOf(numberPosition) ,font2);
			numberOfPosition.setAlignment(1);
			cellNumberOfPosition.addElement(numberOfPosition);
			basket.addCell(cellNumberOfPosition);

			
			PdfPCell cellName = new PdfPCell();
			Paragraph radiator=new Paragraph("Радиатор",font2);
			radiator.setAlignment(1);
			cellName.addElement(radiator);
			basket.addCell(cellName);
			
			PdfPCell cellType = new PdfPCell();
			Paragraph typeRadiator=new Paragraph(rad.getType()+"-"+rad.getSize(),font2);
			typeRadiator.setAlignment(1);
			cellType.addElement(typeRadiator);
			basket.addCell(cellType);
			
			PdfPCell cellCount = new PdfPCell();
			Paragraph countRadiator=new Paragraph(String.valueOf(rad.getCount()) ,font2);
			allCount=allCount+rad.getCount();
			countRadiator.setAlignment(1);
			cellCount.addElement(countRadiator);
			basket.addCell(cellCount);
			
			PdfPCell cellPrice = new PdfPCell();
			Paragraph priceRadiator=new Paragraph(String.format(Locale.CANADA,"%.2f",rad.getPrice()) ,font2);
			priceRadiator.setAlignment(1);
			cellPrice.addElement(priceRadiator);
			basket.addCell(cellPrice);
			
			PdfPCell cellValue = new PdfPCell();
			double valRad=rad.getPrice()*rad.getCount()*100;
			int i1 = (int) Math.round(valRad);
			valRad = (double)i1/100;
			Paragraph valueRadiator=new Paragraph(String.format(Locale.CANADA,"%.2f",valRad),font2);
			valueRadiator.setAlignment(1);
			cellValue.addElement(valueRadiator);
			basket.addCell(cellValue);
			
			PdfPCell cellNDS = new PdfPCell();
			Paragraph rateNDS=new Paragraph("20",font2);
			rateNDS.setAlignment(1);
			cellNDS.addElement(rateNDS);
			basket.addCell(cellNDS);

			PdfPCell cellValueNDS = new PdfPCell();
			double summaNDS=valRad*0.2*100;
			int i2=(int)Math.round(summaNDS);
			summaNDS=(double)i2/100;
			Paragraph valueRadiatorNDS=new Paragraph(String.format(Locale.CANADA,"%.2f",summaNDS),font2);
			valueRadiatorNDS.setAlignment(1);
			cellValueNDS.addElement(valueRadiatorNDS);
			basket.addCell(cellValueNDS);
			
			PdfPCell cellTotalValue = new PdfPCell();
			double total=(valRad+summaNDS)*100;	
			int i3=(int)Math.round(total);
			total=(double)i3/100;
			Paragraph totalValue=new Paragraph(String.format(Locale.CANADA,"%.2f",total),font2);
			totalValue.setAlignment(1);
			cellTotalValue.addElement(totalValue);
			basket.addCell(cellTotalValue);
			
			allSumm=(allSumm+total)*100;
			int i5=(int)Math.round(allSumm);
			allSumm=(double)i5/100;
			
			PdfPCell cellWeight = new PdfPCell();
			Paragraph weightRadiator=new Paragraph(String.format(Locale.CANADA,"%.2f",rad.getPrice()),font2);
			weightRadiator.setAlignment(1);
			cellWeight.addElement(weightRadiator);
			basket.addCell(cellWeight);
			
			PdfPCell cellMass = new PdfPCell();
			Paragraph massRadiator=new Paragraph(String.format(Locale.CANADA,"%.2f",rad.getPrice()*rad.getCount()),font2);
			massRadiator.setAlignment(1);
			cellMass.addElement(massRadiator);
			basket.addCell(cellMass);
			
			numberPosition=numberPosition+1;
		}
		
		PdfPCell cell01 = new PdfPCell();
		basket.addCell(cell01);
		
		PdfPCell cell02 = new PdfPCell();
		Paragraph par02=new Paragraph("ИТОГО:",font2);
		par02.setAlignment(1);
		cell02.addElement(par02);
		basket.addCell(cell02);
		
		PdfPCell cell03 = new PdfPCell();
		basket.addCell(cell03);
		
		PdfPCell cell04 = new PdfPCell();
		Paragraph par04=new Paragraph(String.valueOf(allCount),font2);
		par04.setAlignment(1);
		cell04.addElement(par04);
		basket.addCell(cell04);
		
		PdfPCell cell05 = new PdfPCell();
		basket.addCell(cell05);
		
		PdfPCell cell06 = new PdfPCell();
		Paragraph par06=new Paragraph(" ",font2);
		par06.setAlignment(1);
		cell06.addElement(par06);
		basket.addCell(cell06);
		
		PdfPCell cell07 = new PdfPCell();
		basket.addCell(cell07);
		
		PdfPCell cell08 = new PdfPCell();
		Paragraph par08=new Paragraph(" ",font2);
		par08.setAlignment(1);
		cell08.addElement(par08);
		basket.addCell(cell08);
		
		PdfPCell cell09 = new PdfPCell();
		Paragraph par09=new Paragraph(String.format(Locale.CANADA,"%.2f",allSumm),font2);
		par09.setAlignment(1);
		cell09.addElement(par09);
		basket.addCell(cell09);
		
		PdfPCell cell010 = new PdfPCell();
		basket.addCell(cell010);
		
		PdfPCell cell011 = new PdfPCell();
		Paragraph par011=new Paragraph(" ",font2);
		par011.setAlignment(1);
		cell011.addElement(par011);
		basket.addCell(cell011);
		
		document.add(basket);
		
		Paragraph p8=new Paragraph("Итого к оплате : "+String.format(Locale.CANADA,"%.2f",allSumm)+" byn ",font1);
		p8.setAlignment(0);
		p8.setSpacingAfter(5);
		document.add(p8);
		
		
		int res = (int)allSumm; //целая часть
		double res2 = (allSumm - res)*100; //дробная часть
		int res3=(int)Math.round(res2);
		
		
		Paragraph p9=new Paragraph("Сумма прописью: "+NumbersToString.push(res)+" белорусских рублей "+NumbersToString.push(res3)+ " копеек",font1);
		p9.setAlignment(0);
		p9.setSpacingAfter(5);
		document.add(p9);
		
		Paragraph p10=new Paragraph("При оплате счёт-фактуры в назначении платежа указать ЗА РАДИАТОРЫ ",font3);
		p10.setAlignment(0);
		p10.setSpacingAfter(10);
		document.add(p10);
		document.add(new Paragraph("1) Настоящая счёт-фактура является протоколом согласования цены. Прейскурант цен 2Д от 07.11.2016г",font2));
		document.add(new Paragraph("2) Условия оплаты - 100% предоплата",font2));
		document.add(new Paragraph("3) Условия отгрузки Товара - в течении 45(сорока пяти) дней с момента зачисления денежных средств на р/c Продавца",font2));
		document.add(new Paragraph("4) Условия поставки Товара - самовывоз",font2));
		Paragraph p15=new Paragraph("5) Отгрузка - строго по согласованию сторон. Производится в рабочие дни с 8-00 до 15-30.",font2);
		p15.setAlignment(0);
		p15.setSpacingAfter(10);
		document.add(p15);
		
		PdfPTable t2 = new PdfPTable(2);
		t2.setHorizontalAlignment(0);
		t2.setLockedWidth(true);
		t2.setTotalWidth(500f);
		float[] widthsTable2=new float[]{150f,350f};
		t2.setWidths(widthsTable2);

		PdfPCell c21 = new PdfPCell();
		c21.setBorder(0);
		Paragraph ph1=new Paragraph("ПРОДАВЕЦ:",font1);
		ph1.setAlignment(1);
		c21.addElement(ph1);
		//Paragraph ph3=new Paragraph("Генеральный директор:",font1);
		//ph3.setAlignment(1);
		//c21.addElement(ph3);
		
		Image sign = Image.getInstance("sign.jpg");
		sign.scaleAbsolute(55f,30f);
		sign.setAlignment(1);
		c21.addElement(sign);
		
		t2.addCell(c21);
		
		PdfPCell c22 = new PdfPCell();
		c22.setBorder(0);
		Paragraph ph2=new Paragraph("ПОКУПАТЕЛЬ:",font1);
		ph2.setAlignment(1);
		c22.addElement(ph2);
		
		Image svarmas = Image.getInstance("svarmas.jpg");
		svarmas.scaleAbsolute(55f,72f);
		svarmas.setAlignment(1);
		c22.addElement(svarmas);
		
		t2.addCell(c22);
		document.add(t2);
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}
		document.close();
	}
	
}
