package ru.unlimit;

public class Radiator {
	private String type;
	private String size;
	private int count;
	private Double price;
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public  Radiator(String type,String size,int count,Double price){
		this.type=type;
		this.size=size;
		this.count=count;
		this.price=price;
		
	}
	
	public  Radiator(){
			
	}
	
	
	@Override
	public String toString() {
		return "тип=" + type + ", размер=" + size + ", кол-во=" + count + ", цена за штуку=" + price ;
	}
	
	
}
