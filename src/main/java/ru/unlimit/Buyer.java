package ru.unlimit;

public class Buyer {
	public String name;
	public String email;
	public String phone;
	public String adres;
	public String passport;
	
	public Buyer(){
		
	}

	
	public Buyer(String name, String email, String phone, String adres, String passport) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.adres = adres;
		this.passport = passport;
	}


	@Override
	public String toString() {
		return "Buyer [name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}

}
