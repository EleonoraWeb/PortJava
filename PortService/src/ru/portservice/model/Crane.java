package ru.portservice.model;

import java.util.Date;

//Класс крана
public class Crane {
	//Производительность за минуту
	private int productivity;
	//Тип груза, который может разгружать кран
	private CargoType cargoType;
	
	private Date lastWorkFinished;
	
	//Конструктор
	public Crane(int productivity, CargoType cargoType) {
		this.productivity = productivity;
		this.cargoType = cargoType;
	}
	
	//Геттер производительность
	public int getProductivity() {
		return productivity;
	}
	
	//Геттер тип груза
	public CargoType getCargoType() {
		return cargoType;
	}

	public Date getLastWorkFinished() {
		return lastWorkFinished;
	}

	public void setLastWorkFinished(Date lastWorkFinished) {
		this.lastWorkFinished = lastWorkFinished;
	}
}
