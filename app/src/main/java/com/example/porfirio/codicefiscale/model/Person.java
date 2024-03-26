package com.example.porfirio.codicefiscale.model;

public class Person {
	
	private String surname="";
	private String name="";
	private Data d=new Data(0,0,0);
	private String c_born="";
	private String s=""; // TRUE -> M      FALSE -> F
	
	public Person(){}
	
	public void setSurname(String s){
		this.surname=s;
	}
	
	public void setName(String s){
		this.name=s;
	}

	public void setData(int giorno,int mese,int anno) {
		d.setDay(giorno);
		d.setMonth(mese);
		d.setYear(anno);
		if (!d.valida())
				throw new IllegalArgumentException("Data non valida");
	}
	
	public void setBornCity(String s){
		this.c_born=s;
	}
	
	public void setSex(String b){
		this.s=b;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public String getName(){
		return name;
	}
	
	public String getBornCity(){
		return c_born;
	}
	
	public String getSex(){
		return s;
	}

	public Data getData() {
		return d;
	}
}
