package usersabstract;

import java.util.Date;

public abstract class Users {

	public Users(String name,String surname,String location,String hiringType,String username,String password,Date dateOfBirth,Date assumptionDate) {
		this.name=name;
		this.surname=surname;
		this.location=location;
		this.hiringType=hiringType;
		this.username=username;
		this.password=password;
		this.dateOfBirth=dateOfBirth;
		this.assumptionDate=assumptionDate;
	}
	
	public Users(){
		this.name="Luca";
		this.surname="Abete";
		this.location="Benevento";
		this.hiringType="tempo indeterminato";
		this.username="pipppo";
		this.password="12345";
		this.dateOfBirth=new Date();
		this.assumptionDate=new Date();
	}
	
	public void print() {
		System.out.println(name);
		System.out.println(surname);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHiringType() {
		return hiringType;
	}
	public void setHiringType(String hiringType) {
		this.hiringType = hiringType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getAssumptionDate() {
		return assumptionDate;
	}
	public void setAssumptionDate(Date assumptionDate) {
		this.assumptionDate = assumptionDate;
	}




	private String name,surname,location,hiringType, username, password;
	private Date dateOfBirth,assumptionDate;
	
		
}
