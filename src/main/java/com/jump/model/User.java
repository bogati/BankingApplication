package com.jump.model;

public class User {
	int userId;
	String firstName;
	String lastName;
	int age;
	String addrCountry;
	String addrState;
	String addrCity;
	String phoneNumber;
	String email;
	String password;
	public User(int userId, String firstName, String lastName, int age, String addrCountry, String addrState,
			String addrCity, String phoneNumber, String email, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.addrCountry = addrCountry;
		this.addrState = addrState;
		this.addrCity = addrCity;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
	
	public User(String firstName, String lastName, int age, String addrCountry, String addrState, String addrCity,
			String phoneNumber, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.addrCountry = addrCountry;
		this.addrState = addrState;
		this.addrCity = addrCity;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddrCountry() {
		return addrCountry;
	}
	public void setAddrCountry(String addrCountry) {
		this.addrCountry = addrCountry;
	}
	public String getAddrState() {
		return addrState;
	}
	public void setAddrState(String addrState) {
		this.addrState = addrState;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", addrCountry=" + addrCountry + ", addrState=" + addrState + ", addrCity=" + addrCity
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password + "]";
	}
	
	

}
