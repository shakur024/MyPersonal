package com.heroeducation.model;

public class Hero {
	private int id;
    private String name;
    private String alias;
    private String alignment;
    private String gender;
    private String imageUrl;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAlignment() {
		return alignment;
	}
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", alias=" + alias + ", alignment=" + alignment + ", gender="
				+ gender + ", imageUrl=" + imageUrl + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getAlias()=" + getAlias() + ", getAlignment()=" + getAlignment() + ", getGender()=" + getGender()
				+ ", getImageUrl()=" + getImageUrl() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
    
    

}
