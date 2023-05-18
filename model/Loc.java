package com.heroeducation.model;

public class Loc {
	private int id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Loc [id=" + id + ", name=" + name + ", address=" + address + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
	
	
    
    
    
    
    
    

}
