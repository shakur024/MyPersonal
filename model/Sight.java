package com.heroeducation.model;

import java.time.LocalDateTime;

public class Sight {
	private int id;
    private Hero hero;
    private Loc location;
    private LocalDateTime dateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	public Loc getLocation() {
		return location;
	}
	public void setLocation(Loc location) {
		this.location = location;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
    
    

}
